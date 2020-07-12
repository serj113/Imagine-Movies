package com.serj113.presentation.ui.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MovieDetailViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = TOTAL_FRAGMENT

    override fun createFragment(position: Int) = when (position) {
        0 -> InfoFragment()
        1 -> CastFragment()
        else -> ReviewFragment()
    }

    fun getTitle(position: Int) = when (position) {
        0 -> "Info"
        1 -> "Cast"
        else -> "Review"
    }

    companion object {
        private const val TOTAL_FRAGMENT = 3
    }
}