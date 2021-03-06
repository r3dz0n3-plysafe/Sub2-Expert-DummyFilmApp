package com.dicoding.dummyfilmapp.favourite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.dummyfilmapp.favourite.R
import com.dicoding.dummyfilmapp.favourite.databinding.ActivityFavouriteBinding
import com.dicoding.dummyfilmapp.favourite.di.favouriteModule
import com.dicoding.dummyfilmapp.favourite.ui.fragment.FavPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavouriteActivity : AppCompatActivity() {

    private lateinit var favouriteBinding: ActivityFavouriteBinding

    companion object {
        private val TAB_FAV_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tv_show
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(favouriteBinding.root)

        loadKoinModules(favouriteModule)

        val favPagerAdapter = FavPagerAdapter(supportFragmentManager, lifecycle)
        favouriteBinding.apply {
            setSupportActionBar(toolbarLayout)
            supportActionBar?.title = getString(R.string.title_activity_favourite)
            toolbarLayout.setNavigationOnClickListener { onBackPressed() }

            viewPager2.adapter = favPagerAdapter
            TabLayoutMediator(tabs, viewPager2) { tab, position ->
                tab.text = resources.getString(TAB_FAV_TITLES[position])
            }.attach()

            supportActionBar?.elevation = 0f
        }
    }
}