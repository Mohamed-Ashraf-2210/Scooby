package com.example.scooby.scooby.userProfile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scooby.databinding.FragmentAddPetsBinding


class AddPetsFragment : Fragment() {
    private var binding: FragmentAddPetsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPetsBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {

    }
}