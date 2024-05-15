package com.example.scooby.authentication.signin.login

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.scooby.R
import com.example.scooby.utils.BaseResponse
import com.example.domain.authentication.ForgotPasswordResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater,container,false)
        mContext = requireContext()

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


        binding.btnContinue.setOnClickListener {
            doForgot(it)
        }
        binding.back.setOnClickListener {v ->
            Navigation.findNavController(v).navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }
        return binding.root
    }

    private fun doForgot(v: View) {
        val email = binding.editTextResetPassEmail.editText?.text.toString()
        viewModel.forgotPassword(email)
        if (flag) {
            val action = ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpVerificationFragment(email)
            Navigation.findNavController(v).navigate(action)
        }
    }
    private fun processError(msg: String?) {
        binding.msgErrorEmailOne.visibility = View.VISIBLE
        binding.msgErrorEmailTwo.visibility = View.VISIBLE
        binding.editTextResetPassEmail.apply {
            boxStrokeColor = Color.RED
            hintTextColor = ColorStateList.valueOf(Color.RED)
        }
    }

    private fun processLogin(data: ForgotPasswordResponse?) {
        binding.msgErrorEmailOne.visibility = View.GONE
        binding.msgErrorEmailTwo.visibility = View.GONE
        binding.editTextResetPassEmail.apply {
            boxStrokeColor = Color.alpha(Color.argb(255,81,57,115))
            hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
        }
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }
    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


}