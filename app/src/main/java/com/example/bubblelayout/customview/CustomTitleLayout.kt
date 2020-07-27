package com.example.bubblelayout.customview

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.bubblelayout.R

class CustomTitleLayout : FrameLayout {
    private var leftRes = R.drawable.ic_arrow_forward_black_24dp
    private var centerTitle = ""
    private var rightRes = R.drawable.ic_arrow_back_black_24dp
    lateinit var onLeftClick: (v: View) -> Unit
    private lateinit var tvTitle: TextView
    private lateinit var tvRight: TextView
    private lateinit var ivRight: ImageView

    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleLayout)
        leftRes = typedArray.getResourceId(R.styleable.CustomTitleLayout_leftRes, leftRes)
        centerTitle = typedArray.getString(R.styleable.CustomTitleLayout_centerText) + ""
        rightRes = typedArray.getResourceId(R.styleable.CustomTitleLayout_rightRes, -1)
        val rightText = typedArray.getString(R.styleable.CustomTitleLayout_rightText)
        typedArray.recycle()
        val inflate = LayoutInflater.from(context).inflate(R.layout.layout_title, this, false)
        this.addView(inflate)
        tvTitle = inflate.findViewById<TextView>(R.id.tv_title)
        val ivLeft = inflate.findViewById<ImageView>(R.id.iv_arrow_left)
        ivRight = inflate.findViewById<ImageView>(R.id.iv_right)
        tvRight = inflate.findViewById<TextView>(R.id.tv_right_text)
        ivLeft.setOnClickListener {
            onLeftClick.invoke(it)
        }
        if (!TextUtils.isEmpty(centerTitle))
            tvTitle.text = centerTitle
        ivLeft.setImageResource(leftRes)
        if (!TextUtils.isEmpty(rightText)) {
            tvRight.text = rightText
        }
        if (rightRes != -1) {
            ivRight.setImageResource(rightRes)
        }
    }

    private fun init(context: Context) {

    }

    fun setTitle(text: String) {
        tvTitle.text = text
    }

    fun setRightImage(res: Int) {
        ivRight.setImageResource(res)
    }

    fun setRightText(text: String) {
        tvRight.text = text
    }
}