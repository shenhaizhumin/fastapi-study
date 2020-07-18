package com.example.bubblelayout

import android.app.Application
import com.kongzue.dialog.util.DialogSettings

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DbController.getInstance().init(this)
        DialogSettings.style = (DialogSettings.STYLE.STYLE_IOS)
        DialogSettings.init()
    }
}