package com.example.bubblelayout.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.FriendListAdapter
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_friend_list.*

class FriendListActivity : BaseVMActivity<LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)
        init()
    }

    private fun init() {
        val adapter = FriendListAdapter(R.layout.item_friend_list, null)
        mRvFriends.adapter = adapter
        mViewModel.getFriendList()
        mViewModel.friendListLiveData.observe(this, Observer {
            adapter.setNewInstance(it)
        })
        val emptyView = View.inflate(this, R.layout.layout_lsit_empty, null)
        emptyView.findViewById<TextView>(R.id.tv_msg).text = "还没有添加朋友哦~"
        adapter.setEmptyView(emptyView)
        adapter.setOnItemClickListener { _, _, position ->
            startActivity(Intent(this,ChatActivity::class.java).putExtra("friend_id",adapter.data[position].id).putExtra("friend_nickname",adapter.data[position].nickname))

        }
    }

    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java
}
