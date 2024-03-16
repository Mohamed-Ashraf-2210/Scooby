package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.databinding.FragmentProfileBinding
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var myPetsRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        init()
        //MainActivity().hideBottomNavigation()
        binding.editProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
        binding.favoritesSection.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_petProfileFragment)
        }
        return binding.root
    }
    

    private fun init() {
        profileViewModel.apply {
            getUser()
            profileResult.observe(viewLifecycleOwner) {
                getProfileData(it)
            }
        }
    }

    private fun getProfileData(it: ProfileDetailsResponse?) {
//        val data = it?.data?.data
        //Glide.with(this).load(data?.profileImage).into(binding.profileImage)
        binding.userName.text = it?.data?.data?.name
//            Constant.id.toString()
        //myPetsRV = binding.myPetsRv
        //myPetsRV.adapter = MyPetsAdapter(it!!,requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.myPetsRv.adapter = null
    }
}
