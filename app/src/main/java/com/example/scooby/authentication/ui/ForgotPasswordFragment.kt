package com.example.scooby.authentication.ui

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
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.ForgotPasswordResponse
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
                    flag = true
//                    processLogin(it.data)
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


        binding.btnContinue.setOnClickListener {v->
            if (flag)
                Navigation.findNavController(v).navigate(R.id.action_resetPassword_to_otpVerificationFragment)
        }
        return binding.root
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