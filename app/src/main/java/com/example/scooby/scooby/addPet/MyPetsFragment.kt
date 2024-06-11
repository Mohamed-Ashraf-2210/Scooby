package com.example.scooby.scooby.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentMyPetsBinding
import com.example.scooby.scooby.adapter.MyPetsAddAdapter
import com.example.scooby.scooby.viewmodel.PetsViewModel
import com.example.scooby.utils.BaseResponse

/**
 * First screen to Add Pet
 * display User pets and button to add another pet
 * author: Mohamed Ashraf
 * */
class MyPetsFragment : Fragment() {
    private var binding: FragmentMyPetsBinding? = null
    private lateinit var myPetsViewModel: PetsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) {
            return binding?.root
        }

        binding = FragmentMyPetsBinding.inflate(inflater, container, false)
        myPetsViewModel = ViewModelProvider(this)[PetsViewModel::class.java]
        initView()
        return binding?.root
    }


    private fun initView() {
        clickListeners()
        observeViewModel()
    }

    private fun clickListeners() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            addPetImage.setOnClickListener {
                findNavController().navigate(R.id.action_myPetsFragment_to_addPetsFragment)
            }
        }
    }

    private fun observeViewModel() {
        myPetsViewModel.apply {
            getMyPets()
            myPetsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        //showLoading()
                    }

                    is BaseResponse.Success -> {
                        //stopLoading()
                        if (it.data != null) {
                            binding?.RvMyPetsContent?.adapter =
                                MyPetsAddAdapter(it.data, requireContext())
                        }

                    }

                    is BaseResponse.Error -> {
                        //stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        //stopLoading()
                    }
                }

            }
        }
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.RvMyPetsContent?.adapter = null
        binding = null
    }
}