package com.example.scooby.authentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.databinding.FragmentCreateNewPasswordBinding


class CreateNewPassword : Fragment() {

    private lateinit var binding:FragmentCreateNewPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewPasswordBinding.inflate(layoutInflater)
        initView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initView() {

    }


}