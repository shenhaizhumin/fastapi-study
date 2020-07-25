package com.example.bubblelayout.ui

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ChatAdapter
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.entity.Conversation
import com.example.bubblelayout.entity.MessageEntity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.ChatViewModel
import com.example.bubblelayout.ws.WsManager
import com.google.gson.Gson
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.activity_chat.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class ChatActivity : BaseVMActivity<ChatViewModel>() {
    private lateinit var dbController: DbController
    private lateinit var layoutManager: LinearLayoutManager
    private var userId: Int = 0
    private lateinit var msgObjectName: String
    private lateinit var adapter: ChatAdapter
    private lateinit var msgTitle: String
    private lateinit var avatar_url: String
    private lateinit var friend_avatar_url: String
    private var targetId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        EventBus.getDefault().register(this)
        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(this)
        dbController = DbController.getInstance()
        userId = UserInfoUtil.getUserId()
        val temp = intent.getStringExtra("objectName")
        if (temp != null) {
            msgObjectName = temp
        } else {
            msgObjectName = UUID.randomUUID().toString()
        }

        msgTitle = intent.getStringExtra("msgTitle")
        friend_avatar_url = intent.getStringExtra("friend_avatar_url")
        targetId = intent.getLongExtra("targetId", 0)
        mIvBackIcon.setOnClickListener { finish() }
        mTvFriendNickname.text = msgTitle
        avatar_url = UserInfoUtil.getUserAvatar() + ""
        val data = DbController.getInstance().getMessageList(msgObjectName)
        adapter = ChatAdapter(R.layout.item_chat, data)
        adapter.transform = CornerTransform(this, ScreenUtils.dip2px(this, 4f).toFloat())
        adapter.mineAvatarUrl = "$avatar_url"
        adapter.friendAvatarUrl = "$friend_avatar_url"
        mRvChat.adapter = adapter
        mRvChat.viewTreeObserver.addOnGlobalLayoutListener {
            if (data.size > 0) {
                mRvChat.smoothScrollToPosition(data.size - 1)
            }
        }
        btnSend.setOnClickListener {
            val msg = mEtMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                val chatMsg = MessageEntity()
                chatMsg.content = msg
                chatMsg.userId = userId
                chatMsg.receivedTime = null
                chatMsg.senderUserId = userId
                chatMsg.sentTime = System.currentTimeMillis()
                chatMsg.conversationType = Conversation.ConversationType.PRIVATE.value
                chatMsg.messageDirection = Conversation.MessageDirection.SENT.value
                chatMsg.objectName = msgObjectName
                chatMsg.targetId = targetId.toInt()
                chatMsg.uId = UUID.randomUUID().toString()
//                chatMsg.objectName = UUID.randomUUID().toString()
                chatMsg.setSaved(true)
                chatMsg.messageStatus=Conversation.MessageStatus.MESSAGESENT.value
                dbController.insertMessage(chatMsg)
                //发消息 更新会话消息
                EventBus.getDefault().post(chatMsg)
                WsManager.getInstance().sendMsg(Gson().toJson(chatMsg))
                adapter.addData(chatMsg)
                mRvChat.smoothScrollToPosition(adapter.data.size - 1)
                mEtMessage.setText("")
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(receiveChat: MessageEntity) {
        //收到消息
//        val receiveChat = Gson().fromJson(it, ChatMessageEntity::class.java)
//        receiveChat.ismineChat = 1
//        receiveChat.friend_nickname = friend_nickname
//        receiveChat.nickname = mineNickName
//        receiveChat.friend_avatar_url = friend_avatar_url
//        receiveChat.mine_avatar_url = avatar_url
        if (receiveChat.messageStatus!=Conversation.MessageStatus.MESSAGESENT.value) {
            //只接受服务端发送的消息
            adapter.addData(receiveChat)
            mRvChat.smoothScrollToPosition(adapter.data.size - 1)
            receiveChat.id=null
            dbController.insertMessage(receiveChat)
        }

    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
}
