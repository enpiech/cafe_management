package com.example.cafemanagement.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cafemanagement.R
import com.example.cafemanagement.view.ui.tablelist.TableListFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TableListActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var listTablePager: ListTablePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_list)

        viewPager = findViewById(R.id.viewpager)

        listTablePager = ListTablePagerAdapter(this)
        viewPager.adapter = listTablePager

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabLayout, viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> { tab.text = "Tất cả" }
                    //TODO Move to ViewModel
                    1 -> { tab.text = "Khu A" }
                    2 -> { tab.text = "Khu B" }
                    3 -> { tab.text = "Khu C" }
                }
            }).attach()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private inner class ListTablePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 4

        override fun createFragment(position: Int): Fragment = TableListFragment()
    }
}
