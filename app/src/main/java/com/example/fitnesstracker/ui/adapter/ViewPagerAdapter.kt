package com.example.fitnesstracker.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fitnesstracker.ui.activity.MyActivityScreen
import com.example.fitnesstracker.ui.activity.UsersActivityScreen

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                MyActivityScreen()
            }
            1 -> {
                UsersActivityScreen()
            }
            else -> {
                Fragment()
            }
        }
    }

}