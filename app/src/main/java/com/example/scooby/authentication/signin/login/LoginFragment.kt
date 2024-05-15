package com.example.scooby.authentication.signin.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.data.Constant
import com.example.domain.profile.UserResponse
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.utils.BaseResponse


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        authViewModelObserve()
        binding?.apply {
            tvSignup.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_signupFragment)
            }
            tvForgetPass.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
            btnLogin.setOnClickListener {
                doLogin()
            }
        }
        return binding?.root
    }

    private fun authViewModelObserve() {
        viewModel.loginResult.observe(viewLifecycleOwner) {
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
                    processError(it.msg)
                }

                else -> {
                    stopLoading()
                }
            }
        }
    }


    private fun doLogin() {
        binding?.apply {
            val email = emailTextFiled.editText?.text.toString()
            val password = PasswordTextFiled.editText?.text.toString()
            viewModel.loginUser(email, password)
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

            binding?.apply {
                tvMsgError.visibility = View.GONE
                vView.visibility = View.GONE
                emailTextFiled.apply {
                    boxStrokeColor = Color.alpha(Color.argb(255, 81, 57, 115))
                    hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
                }
                PasswordTextFiled.apply {
                    boxStrokeColor = Color.argb(255, 81, 57, 115)
                    hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
                }
            }
            goToHome()
        }
    }

    private fun processError(msg: String?) {
        binding?.apply {
            tvMsgError.visibility = View.VISIBLE
            vView.visibility = View.VISIBLE
            emailTextFiled.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
            PasswordTextFiled.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
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