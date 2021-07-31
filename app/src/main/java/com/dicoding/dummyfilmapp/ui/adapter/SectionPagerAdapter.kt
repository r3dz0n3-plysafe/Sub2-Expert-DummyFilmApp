package com.dicoding.dummyfilmapp.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.ui.fragment.MovieFragment
import com.dicoding.dummyfilmapp.ui.fragment.TvShowFragment

class SectionPagerAdapter(private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tv_show
        )
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> MovieFragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_TITLES[position])
}