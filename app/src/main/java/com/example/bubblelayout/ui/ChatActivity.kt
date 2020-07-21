package com.example.bubblelayout.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ChatAdapter
import com.example.bubblelayout.api.Urls
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.ChatViewModel
import com.example.bubblelayout.ws.WsManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*

class ChatActivity : BaseVMActivity<ChatViewModel>() {
    private lateinit var mWsManager: WsManager
    private lateinit var dbController: DbController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        init()
    }

    private fun init() {
        dbController = DbController.getInstance()
        mWsManager = WsManager.getInstance()
        val userId = UserInfoUtil.getUserId()
        val friendId = intent.getIntExtra("friend_id", 0)
        val friend_nickname = intent.getStringExtra("friend_nickname")

        mIvBackIcon.setOnClickListener { finish() }
        mTvFriendNickname.text = friend_nickname
        val data = DbController.getInstance().findChatMsgById(userId, friendId)
        val adapter = ChatAdapter(R.layout.item_chat, data)
        mRvChat.adapter = adapter
        btnSend.setOnClickListener {
            val msg = mEtMessage.text.toString().trim()
            if (msg.isNotEmpty()) {
                val chatMsg = ChatMessageEntity()
                chatMsg.content = msg
                chatMsg.friend_id = friendId
                chatMsg.user_id = userId
                chatMsg.ismineChat = 0
                chatMsg.post_date = Date()
                dbController.insertChatMsg(chatMsg)
                mWsManager.sendMsg(Gson().toJson(chatMsg))
            }
        }
        mWsManager.connectToWebSocket(Urls.ws_uri) {

        }

    }

    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
}
