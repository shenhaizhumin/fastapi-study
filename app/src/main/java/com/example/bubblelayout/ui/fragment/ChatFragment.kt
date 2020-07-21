package com.example.bubblelayout.ui.fragment

import com.example.bubblelayout.R
import com.example.bubblelayout.api.Urls
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.entity.P2PMessageEntity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.ChatViewModel
import com.example.bubblelayout.ws.WsManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseVMFragment<ChatViewModel>() {
    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java

    override fun getLayoutId(): Int = R.layout.fragment_chat

    override fun initData() {

        btn_connect.setOnClickListener {
            WsManager.getInstance().connectToWebSocket(Urls.ws_uri){
                //接收消息

            }
        }
        btn_send.setOnClickListener {
            val msg = et_msg.text.toString()
            val msgBody = P2PMessageEntity(UserInfoUtil.getUserId(), msg, 2)
            WsManager.getInstance().sendMsg(Gson().toJson(msgBody))
        }
    }

    override fun onDestroyView() {
        WsManager.getInstance().closeConnect()
        super.onDestroyView()
    }
}