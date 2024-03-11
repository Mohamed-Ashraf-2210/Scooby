package com.example.scooby.authentication.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater)

        binding.signUpBtn.setOnClickListener { nav ->
            Navigation.findNavController(nav).navigate(R.id.action_onboardingFragment_to_signupFragment)
        }
        binding.loginBtn.setOnClickListener { nav ->
            Navigation.findNavController(nav).navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
        binding.exploreTextView.setOnClickListener {
            goToHome()
        }
        return binding.root
    }

    private fun goToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}