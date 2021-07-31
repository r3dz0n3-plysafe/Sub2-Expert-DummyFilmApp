package com.dicoding.dummyfilmapp.favourite.ui.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.dummyfilmapp.favourite.R

class FavPagerAdapter(private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TAB_FAV_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tv_show
        )
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFavouriteFragment()
            1 -> TvShowFavouriteFragment()
            else -> MovieFavouriteFragment()
        }

    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TAB_FAV_TITLES[position])
}