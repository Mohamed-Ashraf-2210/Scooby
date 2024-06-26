package com.example.scooby.scooby.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.scooby.scooby.userProfile.MomentFragment
import com.example.scooby.scooby.userProfile.ReviewFragment

class MyVpAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MomentFragment()
            1 -> ReviewFragment()
            else -> MomentFragment()
        }
    }
}