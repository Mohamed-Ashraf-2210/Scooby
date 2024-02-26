package com.example.scooby.authentication.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.scooby.R
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.ForgotPasswordResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding
import com.example.scooby.databinding.FragmentResetPasswordBinding


class ResetPassword : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater,container,false)
        mContext = requireContext()

        viewModel.forgotPasswordResult.observe(viewLifecycleOwner) {
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


        binding.btnContinue.setOnClickListener {v->
            Navigation.findNavController(v).navigate(R.id.action_resetPassword_to_otpVerificationFragment)
        }
        return binding.root
    }

    private fun processError(msg: String?) {

    }

    private fun processLogin(data: ForgotPasswordResponse?) {

    }

    private fun stopLoading() {

    }

    private fun showLoading() {

    }
    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


}