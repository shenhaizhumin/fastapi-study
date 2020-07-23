package com.example.bubblelayout.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.bubblelayout.DbController
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ChatFriendListAdapter
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.entity.ChatMessageEntity
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

class ChatFragment : BaseVMFragment<ChatViewModel>() {
    override fun createViewModel(): Class<ChatViewModel> = ChatViewModel::class.java
    private var userId: Int = 0
    private lateinit var mDbController: DbController
    private lateinit var adapter: ChatFriendListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_chat

    override fun initData() {
        EventBus.getDefault().register(this)
        mDbController = DbController.getInstance()
        userId = UserInfoUtil.getUserId()
        friendList.setOnClickListener {
            startActivity(Intent(context, ContactActivity::class.java))
        }
//        mDbController.findUserLatestMsg()
        adapter = ChatFriendListAdapter(R.layout.item_chat_list, null)
        adapter.transform = CornerTransform(context, ScreenUtils.dip2px(context, 4f).toFloat())
        val emptyView = View.inflate(context, R.layout.layout_lsit_empty, null)
        emptyView.findViewById<TextView>(R.id.tv_msg).text = "还没有聊天记录哦~"
        adapter.setEmptyView(emptyView)
        mRvChat.adapter = adapter

        mViewModel.friendListLiveData.observe(this, Observer { list ->
            //本地获取最近所有聊天记录
            val latestChats = mDbController.findUserLatestMsg(list.map {
                it.id
            })
            adapter.setNewInstance(latestChats)
        })
        mViewModel.getFriendList()
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(
                Intent(context, ChatActivity::class.java).putExtra(
                    "friend_id",
                    adapter.data[position].friend_id
                ).putExtra("friend_nickname", adapter.data[position].friend_nickname)
                    .putExtra("friend_avatar_url", adapter.data[position].friend_avatar_url)
            )
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(receiveChat: ChatMessageEntity) {
        //收到消息 receiveChat
        //user_id 发送者 -->朋友
        // friend_id 接收者

//        val receiveChat = Gson().fromJson(it, ChatMessageEntity::class.java)
        val entity = adapter.data.firstOrNull { entity ->
            entity.friend_id == receiveChat.friend_id
        }
        if (entity != null) {
            entity.content = receiveChat.content
            receiveChat.ismineChat = 1
            entity.id = null
            val insertChatMsg = mDbController.insertChatMsg(entity)
            Log.e(TAG, "insertMsg:$insertChatMsg")
            adapter.notifyItemMoved(adapter.data.indexOf(entity), 0)
            mRvChat.smoothScrollToPosition(adapter.data.size - 1)
        } else {
            receiveChat.ismineChat = 1
            adapter.addData(receiveChat)
        }

    }

    override fun onDestroyView() {
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }
}