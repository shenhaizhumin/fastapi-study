package com.example.bubblelayout.base

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bubblelayout.utils.StatusBar
import com.example.bubblelayout.utils.ToolsUtil
import com.jaeger.library.StatusBarUtil

open class BaseActivity : AppCompatActivity() {
    val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        StatusBar.setStatusBar(Color.WHITE, this)
//        ToolsUtil.MIUISetStatusBarLightMode(this, true)
        super.onCreate(savedInstanceState)
    }

    fun EditText.afterTextChanged(afterChanged: (s: Editable?) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterChanged.invoke(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}