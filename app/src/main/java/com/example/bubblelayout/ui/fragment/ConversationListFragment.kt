package com.example.bubblelayout.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ConversationListAdapter
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.Conversation
import com.example.bubblelayout.entity.ConversationEntity
import com.example.bubblelayout.entity.MessageEntity
import com.example.bubblelayout.ui.ChatActivity
import com.example.bubblelayout.ui.ContactActivity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.ChatViewModel
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.mRvChat
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ConversationListFragment : BaseVMFragment<ChatViewModel>() {
    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
    private var userId: Int = 0
    private lateinit var mDbController: DbController
    private lateinit var adapter: ConversationListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_chat

    override fun initData() {
        EventBus.getDefault().register(this)
        mDbController = DbController.getInstance()
        userId = UserInfoUtil.getUserId()
        friendList.setOnClickListener {
            startActivity(Intent(context, ContactActivity::class.java))
        }
//        mDbController.findUserLatestMsg()
        adapter = ConversationListAdapter(R.layout.item_conversation_list, null)
        adapter.transform = CornerTransform(context, ScreenUtils.dip2px(context, 4f).toFloat())
        val emptyView = View.inflate(context, R.layout.layout_lsit_empty, null)
        emptyView.findViewById<TextView>(R.id.tv_msg).text = "还没有聊天记录哦~"
        adapter.setEmptyView(emptyView)
        mRvChat.adapter = adapter

        //本地获取最近所有聊天记录
        val latestChats = mDbController.getLatestConversationList(userId)
        adapter.setNewInstance(latestChats)
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(
                Intent(context, ChatActivity::class.java).putExtra(
                    "objectName",
                    adapter.data[position].objectName
                ).putExtra("friend_avatar_url", adapter.data[position].portraitUrl)
                    .putExtra("msgTitle", adapter.data[position].conversationTitle)
            )
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(message: MessageEntity) {
        //收到消息
        /**
         * 找到与该消息相关的会话
         */
        val entity = adapter.data.firstOrNull { entity ->
            entity.objectName == message.objectName
        }
        if (entity != null) {
            /**
             * 更新内容
             */
            entity.latestMessage = message.content
            entity.latestMessageId = message.id.toInt()
            entity.receivedTime = message.sentTime//接收时间
            entity.senderUserId = message.senderUserId//消息内容
            entity.targetId = message.targetId
            val insertChatMsg = mDbController.updateConversation(entity)
            Log.e(TAG, "insertMsg:$insertChatMsg")
            adapter.notifyItemChanged(adapter.data.indexOf(entity))
            mRvChat.smoothScrollToPosition(adapter.data.size - 1)
        } else {
            /**
             * 新增会话
             */
            val conversationEntity = ConversationEntity()
            conversationEntity.targetId = message.targetId
            conversationEntity.latestMessage = message.content
            conversationEntity.latestMessageId = message.id.toInt()
            conversationEntity.conversationType = Conversation.ConversationType.PRIVATE.value
            //根据发送者id查询用户
            val target = mDbController.getUserById(message.senderUserId)
            conversationEntity.conversationTitle = target.nickname
            conversationEntity.portraitUrl = target.avatar_url
            conversationEntity.receivedTime = message.receivedTime
            conversationEntity.sentTime = message.sentTime
            conversationEntity.objectName = message.objectName
            conversationEntity.friendId = target.userId
//            conversationEntity.conversationTitle = message.
            val insertChatMsg = mDbController.insertConversation(conversationEntity)
            Log.e(TAG, "insertMsg:$insertChatMsg")
            adapter.addData(conversationEntity)
        }

    }

    override fun onDestroyView() {
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }
}