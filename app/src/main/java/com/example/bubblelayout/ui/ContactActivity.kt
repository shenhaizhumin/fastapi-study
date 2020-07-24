package com.example.bubblelayout.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.contactAdapter
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.entity.Conversation
import com.example.bubblelayout.entity.ConversationEntity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.LoginViewModel
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : BaseVMActivity<LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        init()
    }

    private fun init() {
        mIvBackIcon.setOnClickListener { finish() }
        val adapter = contactAdapter(R.layout.item_contact, null)
        adapter.transform = CornerTransform(this, ScreenUtils.dip2px(this, 4f).toFloat())
        mRvFriends.adapter = adapter
        mViewModel.getContacts()
        mViewModel.contactLiveData.observe(this, Observer {
            adapter.setNewInstance(it)
        })
        val emptyView = View.inflate(this, R.layout.layout_lsit_empty, null)
        emptyView.findViewById<TextView>(R.id.tv_msg).text = "还没有添加朋友哦~"
        adapter.setEmptyView(emptyView)
        adapter.setOnItemClickListener { _, _, position ->
            //            val conversation = DbController.getInstance()
//                .getConversationEntity(UserInfoUtil.getUserId(), adapter.data[position].userId)
//            val conversationEntity = ConversationEntity()
//            conversationEntity.targetId = message.targetId
//            conversationEntity.latestMessage = message.content
//            conversationEntity.latestMessageId = message.id.toInt()
//            conversationEntity.conversationType = Conversation.ConversationType.PRIVATE.value
//            //根据发送者id查询用户
//            val target = mDbController.getUserById(message.senderUserId)
//            conversationEntity.conversationTitle = target.nickname
//            conversationEntity.portraitUrl = target.avatar_url
//            conversationEntity.receivedTime = message.receivedTime
//            conversationEntity.sentTime = message.sentTime
//            conversationEntity.objectName = message.objectName
//            conversationEntity.friendId = target.userId
////            conversationEntity.conversationTitle = message.
//            val insertChatMsg = mDbController.insertConversation(conversationEntity)
//            Log.e(TAG, "insertMsg:$insertChatMsg")
            startActivity(
                Intent(this, ChatActivity::class.java).putExtra("msgTitle", adapter.data[position].nickname)
                    .putExtra("friend_avatar_url", adapter.data[position].avatar_url)
            )

        }
    }

    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java
}
