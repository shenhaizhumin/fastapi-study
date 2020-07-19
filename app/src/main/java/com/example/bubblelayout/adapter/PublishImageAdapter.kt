package com.example.bubblelayout.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuBuilder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.FileEntity
import com.example.bubblelayout.imageloader.ImageLoader
import me.kareluo.ui.PopupMenuView
import me.kareluo.ui.PopupView

class PublishImageAdapter : BaseQuickAdapter<FileEntity, BaseViewHolder> {
    var onMenuClick: (position: Int) -> Unit
    var mMenuView: PopupMenuView
    private var currentPosition: Int = -1
    var onAddClick: () -> Unit

    constructor(
        context: Context,
        layoutId: Int,
        dataList: MutableList<FileEntity>,
        onMenuClick: (position: Int) -> Unit, onAddClick: () -> Unit
    ) : super(layoutId, dataList) {
        this.onMenuClick = onMenuClick
        this.onAddClick = onAddClick
        // 根据menu资源文件创建
        mMenuView = PopupMenuView(context, R.menu.menu_pop_delete, MenuBuilder(context))
        mMenuView.setSites(PopupView.SITE_TOP)
        mMenuView.setOnMenuClickListener { _, _ ->
            onMenuClick.invoke(currentPosition)
            return@setOnMenuClickListener true
        }

    }

    override fun convert(holder: BaseViewHolder, item: FileEntity) {
        val imageView = holder.getView<ImageView>(R.id.iv_image)
        val addView = holder.getView<View>(R.id.add_image)
        if (holder.absoluteAdapterPosition == 0) {
            addView.visibility = View.VISIBLE
            imageView.visibility = View.GONE
        } else {
            addView.visibility = View.GONE
            imageView.visibility = View.VISIBLE
            ImageLoader.getInstance().loadImage(item.file_url, imageView)
        }
        imageView.setOnLongClickListener {
            currentPosition = holder.absoluteAdapterPosition
            mMenuView.show(it)
            return@setOnLongClickListener true
        }
        addView.setOnClickListener {
            onAddClick.invoke()
        }
    }
}