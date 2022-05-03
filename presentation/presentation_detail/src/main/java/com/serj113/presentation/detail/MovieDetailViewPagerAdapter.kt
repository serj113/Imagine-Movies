package com.serj113.presentation.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.serj113.presentation.detail.info.InfoFragment

class MovieDetailViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = TOTAL_FRAGMENT

    override fun createFragment(position: Int) = InfoFragment()

    fun getTitle(position: Int) = "Info"

    companion object {
        private const val TOTAL_FRAGMENT = 1
    }
}