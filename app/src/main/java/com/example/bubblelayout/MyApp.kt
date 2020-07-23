package com.example.bubblelayout


import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.example.bubblelayout.imageloader.ImageLoader
import com.kongzue.dialog.util.DialogSettings
import com.kongzue.dialog.util.TextInfo
import com.lzy.ninegrid.NineGridView


class MyApp : Application() {


    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        DbController.getInstance().init(this)
        ImageLoader.getInstance().init(this)
        DialogSettings.style = (DialogSettings.STYLE.STYLE_IOS)
        DialogSettings.theme = DialogSettings.THEME.DARK
        DialogSettings.menuTextInfo = TextInfo().apply {
            fontColor = R.color.commonTextColor
        }
        DialogSettings.titleTextInfo = TextInfo().apply {
            fontColor = R.color.commonTextColor
        }
        DialogSettings.init()

        NineGridView.setImageLoader(MyImageLoader())
    }

    /** Picasso 加载  */
    private class MyImageLoader : NineGridView.ImageLoader {
        override fun onDisplayImage(
            context: Context?,
            imageView: ImageView?,
            url: String?
        ) {
            ImageLoader.getInstance().loadImage(url, imageView)
        }

        override fun getCacheImage(url: String): Bitmap? {
            return null
        }
    }
}