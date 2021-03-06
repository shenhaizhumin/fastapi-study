package com.example.bubblelayout.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.bubblelayout.R
import com.example.bubblelayout.api.Urls
import com.example.bubblelayout.base.BaseActivity
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.Conversation
import com.example.bubblelayout.entity.MessageEntity
import com.example.bubblelayout.ui.fragment.*
import com.example.bubblelayout.ws.WsManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus


class HomeActivity : BaseActivity() {
    private var currentFragment: Fragment? = null
    private lateinit var homeFragment: HomeFragment

    //    private var messageFragment: MessageFragment? = null
    private var instagramFragment: InstagramFragment? = null
    private var accountFragment: AccountFragment? = null
    private var momentFragment: MomentFragment? = null
    private var conversationListFragment: ConversationListFragment? = null
    private lateinit var mWsManager: WsManager

    override fun onCreate(savedInstanceState: Bundle?) {
//        window.addFlags(Activity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mWsManager = WsManager.getInstance()
        initView()
        initWs()
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
//            100
//        )

    }

    private fun initWs() {
        mWsManager.connectToWebSocket(Urls.ws_uri) {
            Log.e(TAG, it)
            //收到消息
            try {
                val messageEntity = Gson().fromJson(it, MessageEntity::class.java)
                messageEntity.messageDirection = Conversation.MessageDirection.RECEIVE.value
                EventBus.getDefault().post(messageEntity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        mWsManager.closeConnect()
        super.onDestroy()
    }

    private fun initView() {
        homeFragment = HomeFragment()
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    //首页
                    addFragment(homeFragment)
                }
                R.id.navigation_notifications -> {
                    if (instagramFragment == null) {
                        instagramFragment = InstagramFragment()
                    }
                    addFragment(instagramFragment!!)
                }
                R.id.navigation_account -> {
                    if (accountFragment == null) {
                        accountFragment = AccountFragment()
                    }
                    addFragment(accountFragment!!)
                }
                R.id.navigation_dashboard -> {
                    if (momentFragment == null) {
                        momentFragment = MomentFragment()
                    }
                    addFragment(momentFragment!!)
                }
                R.id.navigation_chat -> {
                    if (conversationListFragment == null) {
                        conversationListFragment = ConversationListFragment()
                    }
                    addFragment(conversationListFragment!!)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        nav_view.selectedItemId = R.id.navigation_home
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (currentFragment != null) {
            transaction.hide(currentFragment!!)
        }
        if (fragment.isAdded) {
            transaction.show(fragment)
        } else {
            transaction.add(R.id.fl_container, fragment, fragment.javaClass.simpleName)
        }
        transaction.commit()
        currentFragment = fragment
    }
}
