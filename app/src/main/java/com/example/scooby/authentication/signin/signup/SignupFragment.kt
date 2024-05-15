package com.example.scooby.authentication.signin.signup

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.data.Constant
import com.example.domain.profile.UserResponse
import com.example.scooby.R
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentSignupBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.TokenManager


class SignupFragment : Fragment() {
    private var binding: FragmentSignupBinding? = null
    private lateinit var viewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        signUpObserve()
        binding?.apply {
            tvSignIn.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_signupFragment_to_loginFragment)
            }
            signUpBtnCreate.setOnClickListener {
                val name = checkName()
                val email = checkEmail()
                val pass = checkPassword()
                if (name && email && pass)
                    doSignUp()
            }
        }
        return binding?.root
    }


    private fun signUpObserve() {
        viewModel.signUpResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    stopLoading()
                }

                else -> {
                    stopLoading()
                }
            }
        }
    }

    private fun checkName(): Boolean {
        binding?.apply {
            val name = nameTextField.editText?.text.toString()
            if (name.isEmpty()) {
                tvMsgError.visibility = View.VISIBLE
                nameTextField.apply {
                    boxStrokeColor = Color.RED
                    hintTextColor = ColorStateList.valueOf(Color.RED)
                }
                return false
            } else {
                tvMsgError.visibility = View.GONE
                nameTextField.apply {
                    boxStrokeColor = Color.argb(255, 81, 57, 115)
                    hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
                }
            }
        }
        return true
    }

    private fun checkEmail(): Boolean {
        binding?.apply {
            val email = emailTextField.editText?.text.toString()
            if (email.isEmpty() || !isEmailValid(email)) {
                msgErrorEmailOne.visibility = View.VISIBLE
                msgErrorEmailTwo.visibility = View.VISIBLE
                emailTextField.apply {
                    boxStrokeColor = Color.RED
                    hintTextColor = ColorStateList.valueOf(Color.RED)
                }
                return false
            } else {
                msgErrorEmailOne.visibility = View.GONE
                msgErrorEmailTwo.visibility = View.GONE
                emailTextField.apply {
                    boxStrokeColor = Color.argb(255, 81, 57, 115)
                    hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
                }
            }
        }
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun checkPassword(): Boolean {
        binding?.apply {
            val password = passwordTextField.editText?.text.toString()
            if (password.length < 8) {
                msgErrorPass.visibility = View.VISIBLE
                passwordTextField.apply {
                    boxStrokeColor = Color.RED
                    hintTextColor = ColorStateList.valueOf(Color.RED)
                }
                return false
            } else {
                msgErrorPass.visibility = View.GONE
                passwordTextField.apply {
                    boxStrokeColor = Color.argb(255, 81, 57, 115)
                    hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
                }
            }
        }
        return true
    }


    private fun doSignUp() {
        binding?.apply {
            val email = emailTextField.editText?.text.toString()
            val name = nameTextField.editText?.text.toString()
            val password = passwordTextField.editText?.text.toString()
            viewModel.signUpUser(email, name, password)
        }
    }

    private fun processLogin(data: UserResponse?) {
        if (!data?.token.isNullOrEmpty()) {
            // Save token
            TokenManager.saveAuth(
                this.requireContext(),
                Constant.USER_TOKEN,
                data?.token.toString()
            )

            // Save user id
            TokenManager.saveAuth(
                this.requireContext(),
                Constant.USER_ID,
                data?.data?.result?.id.toString()
            )
            goToHome()
        }
    }

    private fun goToHome() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        ActivityCompat.finishAffinity(requireActivity())
    }


    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}