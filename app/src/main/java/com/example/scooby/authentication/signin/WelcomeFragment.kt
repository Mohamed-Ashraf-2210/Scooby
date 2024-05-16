package com.example.scooby.authentication.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scooby.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater)

        binding?.apply {
            signUpBtn.setOnClickListener { nav ->
                Navigation.findNavController(nav)
                    .navigate(R.id.action_onboardingFragment_to_signupFragment)
            }
            loginBtn.setOnClickListener { nav ->
                Navigation.findNavController(nav)
                    .navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
            exploreTextView.setOnClickListener {
                goToHome()
            }
        }

        return binding?.root
    }

    private fun goToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}