package com.example.bubblelayout

import android.app.Application

class MyApp:Application() {

    override fun onCreate() {
        super.onCreate()
        DbController.getInstance().init(this)
    }
}