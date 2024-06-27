package com.example.scooby.authentication.signin.login

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.scooby.R
import com.example.scooby.authentication.viewModels.AuthViewModel
import com.example.scooby.databinding.FragmentForgotPasswordBinding
import com.example.scooby.utils.BaseResponse


class ForgotPasswordFragment : Fragment() {
    private var binding: FragmentForgotPasswordBinding? = null
    private lateinit var viewModel: AuthViewModel
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        authViewModelObserve()
        binding?.apply {
            btnContinue.setOnClickListener {
                doForgot(it)
            }
            back.setOnClickListener { v ->
                Navigation.findNavController(v)
                    .navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }
        return binding?.root
    }

    private fun authViewModelObserve() {
        viewModel.forgotPasswordResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                    flag = true
                }

                is BaseResponse.Error -> {
                    stopLoading()
                    flag = false
                    processError(it.msg)
                }

                else -> {
                    stopLoading()
                }
            }
        }
    }

    private fun doForgot(v: View) {
        val email = binding?.editTextResetPassEmail?.editText?.text.toString()
        viewModel.forgotPassword(email)
        if (flag) {
            val action =
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpVerificationFragment(
                    email
                )
            Navigation.findNavController(v).navigate(action)
        }
    }

    private fun processError(msg: String?) {
        binding?.apply {
            msgErrorEmailOne.visibility = View.VISIBLE
            msgErrorEmailTwo.visibility = View.VISIBLE
            editTextResetPassEmail.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
        }

    }

    private fun processLogin(data: ForgotPasswordResponse?) {
        binding?.apply {
            msgErrorEmailOne.visibility = View.GONE
            msgErrorEmailTwo.visibility = View.GONE
            editTextResetPassEmail.apply {
                boxStrokeColor = Color.alpha(Color.argb(255, 81, 57, 115))
                hintTextColor = ColorStateList.valueOf(Color.argb(255, 81, 57, 115))
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