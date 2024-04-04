package com.example.scooby.scooby.userProfile.fragment

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
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentProfileBinding
import com.example.scooby.scooby.userProfile.adapter.MyPetsHomeAdapter
import com.example.scooby.scooby.userProfile.viewModel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        getUserId()
        initView()
        return binding.root
    }


    private fun initView() {
        observeViewModel()
        clickToBack()
        clickToEdit()
        clickToAddPet()
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
        Glide.with(this).load(data?.profileImage).into(binding.profileImage)
        binding.userName.text = data?.name
        binding.myPetsRv.adapter = MyPetsHomeAdapter(it!!, requireContext())
    }

    private fun clickToBack() {
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun clickToEdit() {
        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }

    private fun clickToAddPet() {
        binding.addPetIcon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_myPetsFragment)
        }
    }

    private fun getUserId() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
        binding.userProfileContent.visibility = View.VISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.myPetsRv.adapter = null
    }
}
