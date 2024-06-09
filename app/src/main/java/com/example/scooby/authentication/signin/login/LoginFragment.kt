package com.example.scooby.authentication.signin.login

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.data.Constant
import com.example.data.local.TokenManager
import com.example.domain.profile.UserResponse
import com.example.scooby.R
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.utils.BaseResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
                Constant.USER_TOKEN,
                data?.token.toString()
            )

            // Save user id
            TokenManager.saveAuth(
                Constant.USER_ID,
                data?.data?.result?.id.toString()
            )

            binding?.success?.visibility = View.VISIBLE
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
        runBlocking {
            launch {
                delay(2000)
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                requireActivity().finishAffinity()
            }
        }
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