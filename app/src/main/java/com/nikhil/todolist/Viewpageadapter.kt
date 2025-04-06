package com.nikhil.todolist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
//the adpater code is as it is
class Viewpageadapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    //for number of tabs
    override fun getItemCount(): Int=1
    //mapping
    override fun createFragment(position: Int): Fragment {
        return when (position)
        {
            0->AddNewfrag()

            else ->AddNewfrag()
        }
    }
}