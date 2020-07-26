package com.example.bubblelayout

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_uitest.*
import okhttp3.OkHttpClient

class UITestActivity : AppCompatActivity() {
    private val handler=Handler(Handler.Callback {

        if (it.what == 1) {
            val btn=it.obj as Button
            flContainer.addView(btn)

        } else {
            sendMsg()
        }
        return@Callback  true
    })


    private fun sendMsg(){
//        val message = handler.obtainMessage()
//        message.callback
//        handler.sendMessageDelayed()
        handler.postDelayed({
           val btn= flContainer.getChildAt(0) as Button
            btn.text="${SystemClock.uptimeMillis()}"
            sendMsg()
        },200)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uitest)
        tv_workThread.setOnClickListener {
           Thread( Runnable {
               sendMsg()
               SystemClock.sleep(6000)
               tv_workThread.text="崩溃。。。"
           }).start()
        }

        tv_notifyMainThread.setOnClickListener {
            Thread(Runnable {
//                Looper.prepare()
//                val btn=Button(this@UITestActivity.applicationContext)
//                btn.text="在工作线程创建UI，并添加到主线程更新UI"
//                val message = handler.obtainMessage()
//                message.obj=btn
//                message.what=1
//                handler.sendMessage(message)
//                Looper.loop()
                mainLooper.quit()

            }).start()
        }
        tv_workThreadUpdateToastOrDialog.setOnClickListener {
           Thread(Runnable {
               Looper.prepare()
             val handler= object :Handler(){
                  override fun handleMessage(msg: Message) {
                      super.handleMessage(msg)
                      AlertDialog.Builder(this@UITestActivity).setMessage("hehehehhe").create().show()
                  }
              }
               handler.sendEmptyMessage(0)
               Looper.loop()
           }).start()
        }

    }
}
