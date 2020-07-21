package com.example.bubblelayout.ui.fragment

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.MomentAdapter
import com.example.bubblelayout.api.body.CommentBody
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.ui.PublishMomentActivity
import com.example.bubblelayout.ui.UserMomentActivity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.GlideEngine
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.MomentViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kongzue.dialog.interfaces.OnMenuItemClickListener
import com.kongzue.dialog.util.DialogSettings
import com.kongzue.dialog.v3.BottomMenu
import com.kongzue.dialog.v3.InputDialog
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.fragment_moment.*
import java.io.File
import kotlin.math.abs


class MomentFragment : BaseVMFragment<MomentViewModel>() {
    private lateinit var mAdapter: MomentAdapter
    private var userId: Int = 0
    override fun getLayoutId(): Int = R.layout.fragment_moment
    private lateinit var transform: CornerTransform
    private val limit = 10
    private var offset = 0


    override fun initData() {
//        toolBar.setOnTouchListener { _, event ->
//            return@setOnTouchListener mIvPublish.dispatchTouchEvent(event)
//        }
        userId = UserInfoUtil.getUserId()
        transform = CornerTransform(context, ScreenUtils.dip2px(context, 4f).toFloat())
        mSwipeRefresh.setOnRefreshListener {
            mViewModel.getMoments(userId.toInt())
        }
        mSettingBgAction.setOnClickListener {
            //轻触设置背景
            BottomMenu.show(
                activity as AppCompatActivity,
                arrayOf("更换封面"),
                OnMenuItemClickListener { _, index ->
                    //选择图片
                    if (index == 0)
                        PictureSelector.create(this)
                            .openGallery(PictureMimeType.ofImage())
                            .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                            .forResult(PictureConfig.CHOOSE_REQUEST)
                })
        }
        mViewModel.userLiveData.observe(this, Observer {
            Glide.with(context!!)
                .load(it.moment_image)
                .into(mIvUserMomentBg)
            mTvSetMomentImage.visibility = View.GONE
        })
        if (!TextUtils.isEmpty(UserInfoUtil.getMomentImage())) {
            Glide.with(context!!)
                .load(UserInfoUtil.getMomentImage())
                .into(mIvUserMomentBg)
            mTvSetMomentImage.visibility = View.GONE
        }

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            Log.e(TAG, "offset:$verticalOffset")
            val total = mIvUserImg.height.toFloat()
//            val total = appBarLayout.totalScrollRange.toFloat()
//            val current = abs(verticalOffset)
            if (appBarLayout.totalScrollRange - abs(verticalOffset) <= total) {
                //这个时候变换背景色
                val start = appBarLayout.totalScrollRange - total
                val current = abs(verticalOffset) - start
                val alpha = current / total
                toolBar.alpha = alpha
                mTvSetMomentImage.alpha = 1 - alpha
            } else {
                toolBar.alpha = 0f
                mTvSetMomentImage.alpha = 1f
            }

            when {
                verticalOffset >= 0 -> {
                    //完全展开状态
                    mSwipeRefresh.isEnabled = true
                }
                abs(verticalOffset) >= appBarLayout.totalScrollRange -> {
                    //完全折叠状态
                    mSwipeRefresh.isEnabled = false
                }
                else -> {
                    mSwipeRefresh.isEnabled = false
                }
            }
        })
        mIvPublish.setOnClickListener {
            startActivityForResult(Intent(context, PublishMomentActivity::class.java), 101)
        }


        mTvUserNickname.text = UserInfoUtil.getUserNickname()
        if (!TextUtils.isEmpty(UserInfoUtil.getUserAvatar())) {
            Glide.with(context!!).asBitmap()
                .load(UserInfoUtil.getUserAvatar())
                .apply(
                    RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                        .transform(transform)
                )
                .into(mIvUserImg)
        }

        mAdapter = MomentAdapter(
            context!!,
            R.layout.item_moment,
            null
        ) { item, flag ->
            if (flag == MomentAdapter.menu_collect) {
                //收藏
                mViewModel.collect(item.id)
            } else {
                //评论
                InputDialog.show(activity as AppCompatActivity, "发布评论", "请输入评论")
                    .setOkButton { baseDialog, _, inputStr ->
                        baseDialog.doDismiss()
                        mViewModel.publishComment(
                            CommentBody(
                                moment_id = item.id, content = inputStr
                            )
                        )
                        return@setOkButton true
                    }
            }
        }.apply {
            setEmptyView(
                LayoutInflater.from(context).inflate(R.layout.layout_lsit_empty, null, false)
            )
        }
        mViewModel.collectLiveData.observe(this, Observer {
            mViewModel.getMoments(userId.toInt())
        })
        mRvMoments.adapter = mAdapter
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.fileLiveData.observe(this, Observer {
            //更新用户信息
            mViewModel.updateUser(UserBody().apply {
                moment_image = it[0].file_url
            })
        })
        mViewModel.mMomentListLiveData.observe(this, Observer {
            mAdapter.setNewInstance(it)
            mSwipeRefresh.isRefreshing = false
        })
        mViewModel.mPublishMomentLiveData.observe(this, Observer {
            //朋友圈消息发布成功 刷新页面
            mViewModel.getMoments(userId.toInt())
        })
        //获取朋友圈列表
        mViewModel.getMoments(userId.toInt())
        mViewModel.mPublishCommentListLiveData.observe(this, Observer {
            //评论发布成功
            //刷新当前条目评论列表
            mViewModel.getMoments(userId.toInt())
        })

        mAdapter.setOnItemClickListener { _, _, position ->
            startActivity(
                Intent(context, UserMomentActivity::class.java).putExtra(
                    "user_id",
                    mAdapter.data[position].publisher.id
                )
            )
        }
    }

    override fun createViewModel(): Class<MomentViewModel> = MomentViewModel::class.java

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == 200) {
            //刷新页面
            mViewModel.getMoments(userId.toInt())
        } else if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                val selectList =
                    PictureSelector.obtainMultipleResult(data)
                mViewModel.uploadFiles(arrayListOf(File(selectList[0].realPath)))
            }
        }
    }
}