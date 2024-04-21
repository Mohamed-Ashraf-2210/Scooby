package com.example.scooby.scooby.addPet

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
import com.example.scooby.scooby.adapter.MyPetsAddAdapter
import com.example.scooby.scooby.viewmodel.MyPetsViewModel


class MyPetsFragment : Fragment() {
    private var binding: FragmentMyPetsBinding? = null
    private val myPetsViewModel by viewModels<MyPetsViewModel>()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPetsBinding.inflate(inflater, container, false)
        getUserId()
        initView()
        observeViewModel()
        return binding?.root
    }

    private fun getUserId() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            addPetImage.setOnClickListener {
                findNavController().navigate(R.id.action_myPetsFragment_to_addPetsFragment)
            }
        }
    }

    private fun observeViewModel() {
        myPetsViewModel.apply {
            getMyPets(userId)
            myPetsResult.observe(viewLifecycleOwner) {
                stopLoading()
                binding?.RvMyPetsContent?.adapter = MyPetsAddAdapter(it!!, requireContext())
            }
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
        binding = null
    }
}