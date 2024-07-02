package com.example.scooby.authentication.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.R
import com.example.scooby.authentication.onBoarding.screens.FirstScreen
import com.example.scooby.authentication.onBoarding.screens.SecondScreen
import com.example.scooby.authentication.onBoarding.screens.ThirdScreen
import com.example.scooby.databinding.FragmentUserProfileMomentBinding
import com.example.scooby.databinding.FragmentViewPagerBinding


class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerBinding.inflate(layoutInflater,container,false)
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,lifecycle
        )

        binding.viewPager.adapter = adapter
        return binding.root
    }


}