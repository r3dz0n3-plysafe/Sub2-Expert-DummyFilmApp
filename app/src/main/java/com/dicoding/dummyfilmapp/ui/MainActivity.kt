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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainPagerAdapter = SectionPagerAdapter(this@MainActivity, supportFragmentManager)
        binding.apply {
            viewPager.adapter = mainPagerAdapter
            tabs.setupWithViewPager(viewPager)
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