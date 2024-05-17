package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.Constant
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.R
import com.example.data.local.TokenManager
import com.example.scooby.databinding.FragmentProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.adapter.MyPetsHomeAdapter
import com.example.scooby.scooby.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        initView()
        observeViewModel()
        return binding?.root
    }


    private fun initView() {
        binding?.apply {
            backProfile.setOnClickListener {
                findNavController().popBackStack()
            }
            editProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
            addPetIcon.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_myPetsFragment)
            }
            favoritesSection.setOnClickListener{
                findNavController().navigate(R.id.action_profileFragment_to_favoriteFragment)
            }

        }
    }

    private fun observeViewModel() {
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                stopLoading()
                getProfileData(it)
            }
        }
    }

    private fun getProfileData(it: UserProfileResponse?) {
        val data = it?.data?.data
        Glide.with(this).load(data?.profileImage).into(binding!!.profileImage)
        binding?.apply {
            userName.text = data?.name
            numberFollowersTv.text = data?.followers?.size.toString()
            numberFollowingTv.text = data?.following?.size.toString()
            myPetsRv.adapter = MyPetsHomeAdapter(it!!, requireContext())
        }
    }


    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            userProfileContent.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.myPetsRv?.adapter = null
        binding = null
    }
}
