package com.example.bubblelayout.adapter

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ContentPagerAdapter(val fragments: MutableList<Fragment>, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int = fragments.size
}