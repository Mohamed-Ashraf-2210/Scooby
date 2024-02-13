package com.example.scooby.authentication

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scooby.R
import com.example.scooby.databinding.FragmentOnboardingBinding


class OnboardingFragment : Fragment() {
    private var binding: FragmentOnboardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val spanString = SpannableString("Welcome to Scooby \nyour pet home!")
        spanString.setSpan(ForegroundColorSpan(Color.rgb(81,57,115)),11,17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding!!.welcomeId.text = spanString

        binding!!.signUpBtn.setOnClickListener { nav ->
            Navigation.findNavController(nav).navigate(R.id.action_onboardingFragment_to_signupFragment)
        }
        binding!!.loginBtn.setOnClickListener { nav ->
            Navigation.findNavController(nav).navigate(R.id.action_onboardingFragment_to_loginPage)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

}