package com.example.scooby.scooby.userProfile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.Constant
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentMyPetsBinding
import com.example.scooby.scooby.userProfile.adapter.MyPetsAddAdapter
import com.example.scooby.scooby.userProfile.viewModel.ProfileViewModel


class MyPetsFragment : Fragment() {
    private var binding: FragmentMyPetsBinding? = null
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPetsBinding.inflate(inflater, container, false)
        getUserId()
        initView()
        binding?.addPetImage?.setOnClickListener {
            findNavController().navigate(R.id.action_myPetsFragment_to_addPetsFragment)
        }
        return binding?.root
    }

    private fun getUserId() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
    }

    private fun initView() {
        observeViewModel()
        clickToBack()
    }

    private fun observeViewModel() {
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                stopLoading()
                binding?.RvMyPetsContent?.adapter = MyPetsAddAdapter(it!!, requireContext())
            }
        }
    }

    private fun clickToBack() {
        binding?.backProfile?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            fragmentContent.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.RvMyPetsContent?.adapter = null
    }
}