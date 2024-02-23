package com.example.scooby.authentication.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        mContext = requireContext()

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

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.tvForgetPass.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_resetPassword)
        }
        return binding.root
    }



    private fun doLogin() {
        val email = binding.emailTextFiled.editText?.text.toString()
        val password = binding.PasswordTextFiled.editText?.text.toString()
        viewModel.loginUser(email,password)
    }
    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    private fun processLogin(data: UserResponse?) {
        showToast("Welcome:" + (data?.data?.result?.name ?: ""))
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let { TokenManager.saveAuthToken(this.mContext, it) }
            binding.tvMsgError.visibility = View.GONE
            binding.vView.visibility = View.GONE
            binding.emailTextFiled.apply {
                boxStrokeColor = Color.alpha(Color.argb(255,81,57,115))
                hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
            }
            binding.PasswordTextFiled.apply {
                boxStrokeColor = Color.argb(255,81,57,115)
                hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
            }
            goToHome()
        }
    }
    private fun showLoading(){
        binding.loading.visibility = View.VISIBLE
    }
    private fun stopLoading(){
        binding.loading.visibility = View.GONE
    }
    private fun processError(msg: String?) {
        binding.tvMsgError.visibility = View.VISIBLE
        binding.vView.visibility = View.VISIBLE
        binding.emailTextFiled.apply {
            boxStrokeColor = Color.RED
            hintTextColor = ColorStateList.valueOf(Color.RED)
        }
        binding.PasswordTextFiled.apply {
            boxStrokeColor = Color.RED
            hintTextColor = ColorStateList.valueOf(Color.RED)
        }
    }
    private fun goToHome() {
        val intent = Intent(mContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}