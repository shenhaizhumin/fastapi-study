package com.example.bubblelayout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ChatAdapter
import com.example.bubblelayout.api.Urls
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.entity.ChatMessageEntity
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
    private var friendId: Int = 0
    private lateinit var friend_nickname: String
    private lateinit var friend_avatar_url: String
    private lateinit var avatar_url: String
    private lateinit var mineNickName: String
    private lateinit var adapter: ChatAdapter

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
        friendId = intent.getIntExtra("friend_id", 0)
        friend_nickname = intent.getStringExtra("friend_nickname")
        friend_avatar_url = intent.getStringExtra("friend_avatar_url")
        mineNickName = UserInfoUtil.getUserNickname() + ""
//        val chatEntity = intent.getSerializableExtra("chatEntity") as ChatMessageEntity
        mIvBackIcon.setOnClickListener { finish() }
        mTvFriendNickname.text = friend_nickname
        avatar_url = UserInfoUtil.getUserAvatar() + ""
        val data = DbController.getInstance().findChatMsgById(userId, friendId)
        data.forEach {
            it.friend_avatar_url = friend_avatar_url
            it.mine_avatar_url = avatar_url
        }
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
                val chatMsg = ChatMessageEntity()
                chatMsg.content = msg
                chatMsg.friend_nickname = friend_nickname
                chatMsg.nickname = mineNickName
                chatMsg.friend_avatar_url = friend_avatar_url
                chatMsg.mine_avatar_url = avatar_url
                chatMsg.friend_id = friendId
                chatMsg.user_id = userId
                chatMsg.ismineChat = 0 //发送者标记为自己
                chatMsg.post_date = System.currentTimeMillis()
                val insertChatMsg = dbController.insertChatMsg(chatMsg)
                Log.e(TAG, "insertMsg:$insertChatMsg")
                //发消息
                WsManager.getInstance().sendMsg(Gson().toJson(chatMsg))
                adapter.addData(chatMsg)
                mRvChat.smoothScrollToPosition(adapter.data.size - 1)
                mEtMessage.setText("")
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(receiveChat: ChatMessageEntity) {
        //收到消息
//        val receiveChat = Gson().fromJson(it, ChatMessageEntity::class.java)
        receiveChat.ismineChat = 1
//        receiveChat.friend_nickname = friend_nickname
//        receiveChat.nickname = mineNickName
//        receiveChat.friend_avatar_url = friend_avatar_url
//        receiveChat.mine_avatar_url = avatar_url
        dbController.insertChatMsg(receiveChat)
        adapter.addData(receiveChat)
        mRvChat.smoothScrollToPosition(adapter.data.size - 1)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
}
