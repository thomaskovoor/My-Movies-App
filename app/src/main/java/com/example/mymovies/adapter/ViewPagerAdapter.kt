package com.example.mymovies.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymovies.ui.PopularMoviesFragment
import com.example.mymovies.ui.TopRatedMoviesFragment

class ViewPagerAdapter(activity: AppCompatActivity, private val totalTabs: Int) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopRatedMoviesFragment() // Replace with your fragment
            1 -> PopularMoviesFragment() // Replace with your fragment
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int {
        return totalTabs
    }
}