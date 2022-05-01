package com.example.fitnesstracker.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitnesstracker.R
import com.example.fitnesstracker.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ActivityScreen : Fragment(R.layout.activity_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.TabLayout)
        val pager = view.findViewById<ViewPager2>(R.id.Pager)

        val adapter = ViewPagerAdapter(this)

        pager.adapter = adapter
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Моя"
                }
                1 -> {
                    tab.text = "Пользователей"
                }
            }
        }.attach()
    }
}