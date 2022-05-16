package com.example.motasemesaefanitunes

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.motasemesaefanitunes.adapter.MusicAdapter
import com.example.motasemesaefanitunes.adapter.ViewPageAdapter
import com.example.motasemesaefanitunes.api.ApiService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit var MusicAdapter: MusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = ViewPageAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "Rock"
                }
                1 -> {
                    "Classic"
                }
                2 -> {
                    "Pop"
                }
                else -> {
                    throw Resources.NotFoundException("Position Not Found")
                }
            }
            tab.icon = when (index) {
                0 -> {
                    getDrawable(R.drawable.ic_baseline_music_note_24)
                }
                1 -> {
                    getDrawable(R.drawable.ic_baseline_music_note_24)
                }
                2 -> {
                    getDrawable(R.drawable.ic_baseline_music_note_24)
                }
                else -> {
                    throw Resources.NotFoundException("Position Not Found")
                }
            }
        }.attach()


    }
}






