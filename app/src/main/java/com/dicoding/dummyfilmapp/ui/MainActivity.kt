package com.dicoding.dummyfilmapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.databinding.ActivityMainBinding
import com.dicoding.dummyfilmapp.ui.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private val TAB_TITLES = intArrayOf(
            R.string.tab_movie,
            R.string.tab_tv_show
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainPagerAdapter = SectionPagerAdapter(supportFragmentManager, lifecycle)
        binding.apply {
            viewPager2.adapter = mainPagerAdapter
            TabLayoutMediator(tabs, viewPager2) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()

            supportActionBar?.elevation = 0f
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.menu_fav -> {
                val uri = Uri.parse("dummyfilmapp://favourite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }
}