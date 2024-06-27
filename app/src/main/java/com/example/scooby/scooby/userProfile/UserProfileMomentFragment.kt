package com.example.scooby.scooby.userProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.databinding.FragmentUserProfileMomentBinding
import com.example.scooby.scooby.adapter.MyVpAdapter
import com.example.scooby.scooby.viewModels.ProfileViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UserProfileMomentFragment : Fragment() {
    private var _binding: FragmentUserProfileMomentBinding? = null
    private lateinit var profileViewModel: ProfileViewModel
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileMomentBinding.inflate(inflater,container,false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        observeOnUserData()
        initViewPager()
        return binding.root
    }
    private fun observeOnUserData() {
        profileViewModel.apply {
            getUserInfo()
            profileResult.observe(viewLifecycleOwner){

            }
        }
    }
    private fun setData2Ui(data:UserProfileResponse) {
        binding.apply {

        }
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