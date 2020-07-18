package com.example.bubblelayout.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.MomentAdapter
import com.example.bubblelayout.api.body.CommentBody
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.entity.MomentEntity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.MomentViewModel
import com.kongzue.dialog.v3.InputDialog
import kotlinx.android.synthetic.main.fragment_moment.*

class MomentFragment : BaseFragment() {
    private lateinit var mAdapter: MomentAdapter
    private var userId: Long = 0
    override fun getLayoutId(): Int = R.layout.fragment_moment


    override fun initData() {
        userId = UserInfoUtil.getUserId()
        mAdapter = MomentAdapter(
            R.layout.item_moment,
//            listOf(
//                MomentEntity(),
//                MomentEntity(),
//                MomentEntity(),
//                MomentEntity(),
//                MomentEntity(),
//                MomentEntity()
//            ).toMutableList()
            null
        )
        mRvMoments.adapter = mAdapter
        val viewModel = ViewModelProvider(this).get(MomentViewModel::class.java)
        viewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        viewModel.mMomentListLiveData.observe(this, Observer {
            mAdapter.setNewInstance(it)
        })
        viewModel.mPublishMomentListLiveData.observe(this, Observer {
            //朋友圈消息发布成功 刷新页面
            viewModel.getMoments(userId.toInt())
        })
        //获取朋友圈列表
        viewModel.getMoments(userId.toInt())
        viewModel.mPublishCommentListLiveData.observe(this, Observer {
            //评论发布成功
            //刷新当前条目评论列表
            viewModel.getMoments(userId.toInt())
        })

        mAdapter.setOnItemClickListener { _, _, position ->
            InputDialog.show(activity as AppCompatActivity, "发布评论", "请输入评论")
                .setOkButton { baseDialog, _, inputStr ->
                    baseDialog.doDismiss()
                    viewModel.publishComment(
                        CommentBody(
                            moment_id = mAdapter.data[position].id, content = inputStr
                        )
                    )
                    return@setOkButton true
                }
        }
    }
}