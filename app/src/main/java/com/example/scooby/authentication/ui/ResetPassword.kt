package com.example.scooby.authentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scooby.R
import com.example.scooby.databinding.FragmentResetPasswordBinding


class ResetPassword : Fragment() {
   private lateinit var binding:FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        initNavigation()
        return binding.root
    }

    private fun initNavigation() {
        binding.btnContinue.setOnClickListener {v->
            Navigation.findNavController(v).navigate(R.id.action_resetPassword_to_createNewPassword)
        }
    }


}