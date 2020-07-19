package com.example.bubblelayout.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.UserMomentAdapter
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.MomentViewModel
import com.google.android.material.appbar.AppBarLayout
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.activity_user_moment.*
import kotlinx.android.synthetic.main.activity_user_moment.appBarLayout
import kotlinx.android.synthetic.main.activity_user_moment.mIvUserImg
import kotlinx.android.synthetic.main.activity_user_moment.mIvUserMomentBg
import kotlinx.android.synthetic.main.activity_user_moment.mRvMoments
import kotlinx.android.synthetic.main.activity_user_moment.mTvUserNickname

class UserMomentActivity : BaseVMActivity<MomentViewModel>() {
    private lateinit var mAdapter: UserMomentAdapter
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_moment)
        userId = intent.getIntExtra("user_id", 0)
        init()
    }

    private fun init() {
//        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
//            Log.e(TAG, "offset:$verticalOffset")
//            val total = mIvUserImg.height.toFloat()
////            val total = appBarLayout.totalScrollRange.toFloat()
////            val current = abs(verticalOffset)
//            if (appBarLayout.totalScrollRange - abs(verticalOffset) <= total) {
//                //这个时候变换背景色
//                val start = appBarLayout.totalScrollRange - total
//                val current = abs(verticalOffset) - start
//                val alpha = current / total
//                toolBar.alpha = alpha
//                mTvSetMomentImage.alpha = 1 - alpha
//            } else {
//                toolBar.alpha = 0f
//            }
//
//        })
        val transform = CornerTransform(this, ScreenUtils.dip2px(this, 4f).toFloat())
        mIvBack.setOnClickListener { finish() }
        mViewModel.mFriendMomentsLiveData.observe(this, Observer {
            mTvUsername.text = it.user.nickname
            mTvUserNickname.text = it.user.nickname
            Glide.with(this).asBitmap()
                .load(UserInfoUtil.getUserAvatar())
                .apply(
                    RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                        .transform(transform)
                )
                .into(mIvUserImg)
            Glide.with(this)
                .load(it.user.moment_image)
                .into(mIvUserMomentBg)
            mAdapter.setNewInstance(it.list.toMutableList())
        })
        mAdapter = UserMomentAdapter(R.layout.item_user_moment, null)
        mAdapter.setEmptyView(
            LayoutInflater.from(this).inflate(R.layout.layout_lsit_empty, null, false)
        )

        mRvMoments.adapter = mAdapter
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.getFriendMoments(userId.toLong())
    }

    override fun createViewModel(): Class<MomentViewModel> = MomentViewModel::class.java
}
