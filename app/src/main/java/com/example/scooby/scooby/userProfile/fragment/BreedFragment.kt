package com.example.scooby.scooby.userProfile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.R
import com.example.scooby.databinding.FragmentAddPetsBinding
import com.example.scooby.databinding.FragmentBreedBinding


class BreedFragment : Fragment() {
    private var binding: FragmentBreedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {

    }

}