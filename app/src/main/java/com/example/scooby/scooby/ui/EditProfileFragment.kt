package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.databinding.FragmentEditProfileBinding
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import com.example.scooby.utils.Constant
import com.example.scooby.utils.TokenManager


class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater)
        init()
        return binding.root
    }

    private fun init() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                getProfileData(it)
            }
        }
        backOffFragment()
    }

    private fun getProfileData(it: ProfileDetailsResponse?) {
        val data = it?.data?.data
        Glide.with(this).load(data?.profileImage).into(binding.profileImage)
        binding.nameEditText.setText(data?.name)
        binding.emailEditText.setText(data?.email)
    }

    private fun backOffFragment() {
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).showBottomNavigationView()
    }
}