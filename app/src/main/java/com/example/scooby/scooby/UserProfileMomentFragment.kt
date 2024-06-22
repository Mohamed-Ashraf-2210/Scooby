package com.example.scooby.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.scooby.R
import com.example.scooby.databinding.FragmentCreatePostBinding
import com.example.scooby.databinding.FragmentProductBinding
import com.example.scooby.databinding.FragmentUserProfileMomentBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UserProfileMomentFragment : Fragment() {
    private var _binding: FragmentUserProfileMomentBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileMomentBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        initViewPager()
        return binding.root
    }

    private fun initViewPager() {
        val mViewPager : ViewPager2 = binding.MyViewPager
        val mTabLayout : TabLayout  = binding.tabLayout

        mViewPager.adapter = MyVpAdapter(requireActivity())
        TabLayoutMediator(mTabLayout,mViewPager){tab,postion ->
            when(postion){
                0 -> tab.text = "Moment"
                1 -> tab.text = "Reviews"
            }
        }.attach()
    }

}