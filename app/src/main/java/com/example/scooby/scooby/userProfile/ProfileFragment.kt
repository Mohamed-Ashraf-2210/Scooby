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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initView()
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
