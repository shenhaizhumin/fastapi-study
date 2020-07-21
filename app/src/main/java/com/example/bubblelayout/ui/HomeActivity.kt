package com.example.bubblelayout.ui

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.base.BaseActivity
import com.example.bubblelayout.imageloader.ImageLoader
import com.example.bubblelayout.ui.fragment.*
import com.example.bubblelayout.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {
    private var currentFragment: Fragment? = null
    private lateinit var homeFragment: HomeFragment
    //    private var messageFragment: MessageFragment? = null
    private var instagramFragment: InstagramFragment? = null
    private var accountFragment: AccountFragment? = null
    private var momentFragment: MomentFragment? = null
    private var chatFragment: ChatFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        window.addFlags(Activity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
//            100
//        )

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
                    if (chatFragment == null) {
                        chatFragment = ChatFragment()
                    }
                    addFragment(chatFragment!!)
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
