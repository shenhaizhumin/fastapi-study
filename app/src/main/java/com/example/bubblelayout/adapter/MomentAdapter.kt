package com.example.bubblelayout.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.MomentEntity
import com.example.bubblelayout.imageloader.ImageLoader
import com.example.bubblelayout.utils.CornerTransform
import com.luck.picture.lib.tools.ScreenUtils.dip2px
import com.lzy.ninegrid.ImageInfo
import com.lzy.ninegrid.NineGridView
import com.lzy.ninegrid.preview.NineGridViewClickAdapter
import me.kareluo.ui.PopupMenuView
import me.kareluo.ui.PopupView
import java.util.regex.Pattern

class MomentAdapter :
    BaseQuickAdapter<MomentEntity, BaseViewHolder> {
    var onMenuClick: (item: MomentEntity, flag: Int) -> Unit
    var mMenuView: PopupMenuView
    private lateinit var entity: MomentEntity

    val regexStr = "~"
    var pattern: Pattern
    var transform: CornerTransform

    companion object {
        val menu_collect = 0//收藏
        val menu_comment = 1//评论
    }

    constructor(
        context: Context,
        layoutId: Int,
        dataList: MutableList<MomentEntity>?,
        onMenuClick: (item: MomentEntity, flag: Int) -> Unit
    ) : super(layoutId, dataList) {
        transform = CornerTransform(context, dip2px(context, 2f).toFloat())
        this.onMenuClick = onMenuClick
        // 根据menu资源文件创建
        mMenuView = PopupMenuView(context, R.menu.menu_pop, MenuBuilder(context))
        mMenuView.setSites(PopupView.SITE_LEFT)
        mMenuView.setOnMenuClickListener { position, _ ->
            onMenuClick.invoke(entity, position)
            return@setOnMenuClickListener true
        }
        pattern = Pattern.compile(regexStr)
    }

    override fun convert(holder: BaseViewHolder, item: MomentEntity) {
        holder.setText(R.id.tv_item_user_name, item.publisher.nickname)
            .setText(R.id.tv_item_content, item.content)
            .setText(R.id.tv_item_time_ago, item.release_time)
        val itemImg = holder.getView<ImageView>(R.id.iv_item_user_icon)
//        ImageLoader.getInstance().loadImage(item.publisher.avatar_url, itemImg)
        Glide.with(context).asBitmap().load(item.publisher.avatar_url)
            .apply(RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).skipMemoryCache(true).transform(transform))
            .into(itemImg)
        val rvComments = holder.getView<RecyclerView>(R.id.rv_item_comments)
        val commentAdapter = CommentAdapter(R.layout.item_comment, item.comments)
        rvComments.adapter = commentAdapter
        val menu = holder.getView<View>(R.id.tv_item_option)
        menu.setOnClickListener {
            mMenuView.show(it)
            entity = item
        }
        val collectView = holder.getView<View>(R.id.item_collect_view)
        val collectTv = holder.getView<TextView>(R.id.tv_item_collects)
        collectTv.text = ""
        if (item.collects != null && item.collects.size > 0) {
            collectView.visibility = View.VISIBLE
            var text = ""
            item.collects.forEach {
//                collectTv.append("${it.publisher.nickname},")
                text += "$regexStr${it.publisher.nickname},"
            }
            val builder = SpannableStringBuilder(text)
            val matcher = pattern.matcher(text)
            while (matcher.find()) {
                builder.setSpan(
                    ImageSpan(context, R.drawable.heart),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            collectTv.text = builder
        } else {
            collectView.visibility = View.GONE
        }
//        val rvPhoto = holder.getView<RecyclerView>(R.id.rv_item_photo)
//        val imageAdapter = ImageAdapter(R.layout.item_image_moment, item.images.map {
//            it.file_url
//        }.toMutableList())
//        rvPhoto.adapter = imageAdapter
        val nineGridView = holder.getView<NineGridView>(R.id.rv_item_photo)
        nineGridView.setAdapter(NineGridViewClickAdapter(context, item.images.map {
            val info = ImageInfo()
            info.setBigImageUrl(it.file_url)
            info.setThumbnailUrl(it.file_url)
            info
        }))
    }
}