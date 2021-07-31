package com.dicoding.dummyfilmapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.dummyfilmapp.ui.fragment.MovieFragment
import com.dicoding.dummyfilmapp.ui.fragment.TvShowFragment

class SectionPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> MovieFragment()
        }
}