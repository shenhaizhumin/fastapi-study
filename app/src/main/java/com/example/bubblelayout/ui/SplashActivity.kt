package com.example.bubblelayout.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bubblelayout.R
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBarUtil.setTranslucent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mIvSplash.post {
            mIvSplash.pivotX = 0f
            mIvSplash.pivotY = mIvSplash.height.toFloat()
            mIvSplash.animate().rotation(0f).setDuration(1000).start()
        }

        mFlEnter.animate().translationX(0f).setDuration(1000).start()
//        mFlEnter.animate().rotationY(100f)
        mFlEnter.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
//        Handler()
//            .postDelayed(
//                {
//                    startActivity(Intent(this, MainActivity::class.java))
//                }, 3000
//            )
    }
}
