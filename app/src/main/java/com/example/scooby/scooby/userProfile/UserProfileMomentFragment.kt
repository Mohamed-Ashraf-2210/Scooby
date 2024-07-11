package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.profile.UserProfileResponse
import com.example.domain.profile.UserResponseX
import com.example.scooby.databinding.FragmentUserProfileMomentBinding
import com.example.scooby.scooby.adapter.MyVpAdapter
import com.example.scooby.scooby.viewModels.ProfileViewModel
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.SharedViewModel
import com.example.scooby.utils.loadUrl
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class UserProfileMomentFragment : Fragment() {
    private val args: UserProfileMomentFragmentArgs by navArgs()
    private var _binding: FragmentUserProfileMomentBinding? = null
    private lateinit var profileViewModel: ProfileViewModel
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileMomentBinding.inflate(inflater,container,false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        observeOnUserData()
        initViewPager()
        navUserId2Fragment()
        return binding.root
    }

    private fun navUserId2Fragment() {
//        val action = UserProfileMomentFragmentDirections.actionUserProfileMomentFragmentToMomentFragment(args.userId)
//        findNavController().navigate(action)
//        val action2 =UserProfileMomentFragmentDirections.actionUserProfileMomentFragmentToReviewFragment(args.userId)
//        findNavController().navigate(action2)
    }

    private fun observeOnUserData() {
        profileViewModel.apply {
            Log.i("checkUserId","in Profile "+args.userId)
            sharedViewModel.userId = args.userId
            getUserById(args.userId)
            userDetailsResult.observe(viewLifecycleOwner){
                when(it){
                    is BaseResponse.Loading ->{
//                        showLoading()
                    }
                    is BaseResponse.Success->{
//                        stopLoading()
                        it.data?.let { it1 -> setData2Ui(it1) }
                    }
                    is BaseResponse.Error ->{
//                        stopLoading()
                    }
                }
            }
        }
    }
    private fun setData2Ui(data:UserResponseX) {
        binding.apply {
            userImg.loadUrl(data.data?.data?.profileImage.toString())
            userName.text = data.data?.data?.name.toString()
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

//    private fun stopLoading() {
//        binding.loading.visibility = View.GONE
//    }
//
//    private fun showLoading() {
//        binding.loading.visibility = View.VISIBLE
//    }

}