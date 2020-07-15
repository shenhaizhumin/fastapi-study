package com.example.bubblelayout.widget

import android.R.attr.path
import android.R.attr.radius
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundRectImageView(context: Context, attributeSet: AttributeSet) :
    AppCompatImageView(context, attributeSet) {
    private val path = Path()
    private lateinit var srcRectF: RectF
    private lateinit var srcRadii: FloatArray
    private var isCircle = false
    private var radius = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = w / 2f
        srcRectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        srcRadii = floatArrayOf(
            10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f
        )
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // 使用离屏缓存，新建一个srcRectF区域大小的图层
        canvas.saveLayer(srcRectF, null, Canvas.ALL_SAVE_FLAG)
        // ImageView自身的绘制流程，即绘制图片
        super.onDraw(canvas)
        // 给path添加一个圆角矩形或者圆形
        if (isCircle) {
            path.addCircle(width / 2.0f, height / 2.0f, radius, Path.Direction.CCW)
        } else {
            path.addRoundRect(srcRectF, srcRadii, Path.Direction.CCW)
        }
        paint.isAntiAlias = true
        // 画笔为填充模式
        paint.setStyle(Paint.Style.FILL)
        // 设置混合模式
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_IN))
        // 绘制path
        canvas.drawPath(path, paint)
        // 清除Xfermode
        paint.setXfermode(null)
        // 恢复画布状态
        canvas.restore()
    }
}