package com.example.bubblelayout.ui.fragment

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ChatFriendListAdapter
import com.example.bubblelayout.api.Urls
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.ui.ChatActivity
import com.example.bubblelayout.ui.FriendListActivity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.ChatViewModel
import com.example.bubblelayout.ws.WsManager
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseVMFragment<ChatViewModel>() {
    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
    private var userId: Int = 0

    override fun getLayoutId(): Int = R.layout.fragment_chat

    override fun initData() {
        userId = UserInfoUtil.getUserId()
        btn_connect.setOnClickListener {
            WsManager.getInstance().connectToWebSocket(Urls.ws_uri) {
                //接收消息

            }
        }
        friendList.setOnClickListener {
            startActivity(Intent(context, FriendListActivity::class.java))
        }
        btn_send.setOnClickListener {
            val msg = et_msg.text.toString()
//            val msgBody = P2PMessageEntity(UserInfoUtil.getUserId(), msg, 2)
//            WsManager.getInstance().sendMsg(Gson().toJson(msgBody))
        }
        val adapter = ChatFriendListAdapter(R.layout.item_chat_list, null)
        val emptyView = View.inflate(context, R.layout.layout_lsit_empty, null)
        emptyView.findViewById<TextView>(R.id.tv_msg).text = "还没有聊天记录哦~"
        adapter.setEmptyView(emptyView)
        mRvChat.adapter = adapter


        mViewModel.friendListLiveData.observe(this, Observer { list ->
            //本地获取最近所有聊天记录
            val latestChats = DbController.getInstance().findUserLatestMsg(list.map {
                it.id
            })
            adapter.setNewInstance(latestChats)
        })
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(
                Intent(context, ChatActivity::class.java).putExtra(
                    "chatEntity",
                    adapter.data[position]
                )
            )
        }
    }

    override fun onDestroyView() {
        WsManager.getInstance().closeConnect()
        super.onDestroyView()
    }
}