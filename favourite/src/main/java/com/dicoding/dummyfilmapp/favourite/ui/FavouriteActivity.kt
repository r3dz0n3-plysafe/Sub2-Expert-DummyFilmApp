package com.dicoding.dummyfilmapp.favourite.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.dummyfilmapp.favourite.R
import com.dicoding.dummyfilmapp.favourite.databinding.ActivityFavouriteBinding
import com.dicoding.dummyfilmapp.favourite.di.favouriteModule
import com.dicoding.dummyfilmapp.favourite.ui.fragment.FavPagerAdapter
import org.koin.core.context.loadKoinModules

class FavouriteActivity : AppCompatActivity() {

    private lateinit var favouriteBinding: ActivityFavouriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(favouriteBinding.root)

        loadKoinModules(favouriteModule)

        val favPagerAdapter = FavPagerAdapter(this@FavouriteActivity, supportFragmentManager)
        favouriteBinding.apply {
            setSupportActionBar(toolbarLayout)
            supportActionBar?.title = getString(R.string.title_activity_favourite)
            toolbarLayout.setNavigationOnClickListener { onBackPressed() }
            viewPager.adapter = favPagerAdapter
            tabs.setupWithViewPager(viewPager)
            supportActionBar?.elevation = 0f
        }
    }
}