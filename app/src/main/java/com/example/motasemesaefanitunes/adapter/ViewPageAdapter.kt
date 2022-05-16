package com.example.motasemesaefanitunes.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.motasemesaefanitunes.Fragments.SongFragment

class ViewPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SongFragment.newInstance(1)
            }
            1 -> {
                SongFragment.newInstance(2)
            }
            2 -> {
                SongFragment.newInstance(3)
            }
            else -> {
                throw Resources.NotFoundException("not found")
            }
        }

    }
}