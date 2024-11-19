package com.example.test_task

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test_task.history.ui.HistoryFragment
import com.example.test_task.search.ui.SearchFragment

const val TAB_SIZE = 2

class MediaAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return TAB_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> SearchFragment.newInstance()
            else -> HistoryFragment.newInstance()
        }
    }

}