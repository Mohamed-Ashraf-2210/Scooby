package com.example.scooby.authentication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.authentication.TokenManager
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding
import com.example.scooby.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater,container,false)
        mContext = requireContext()
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
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }
        binding.signUpBtnCreate.setOnClickListener {
            doSignUp()
        }
        binding.tvSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
        }

        return binding.root
    }
    private fun doSignUp() {
        val email = binding.emailTextField.editText?.text.toString()
        val name = binding.nameTextField.editText?.text.toString()
        val password = binding.passwordTextField.editText?.text.toString()
        viewModel.signUpUser(email,name,password)
    }
    private fun processLogin(data: UserResponse?) {
        showToast("Welcome:" + (data?.data?.result?.name ?: ""))
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let { TokenManager.saveAuthToken(this.mContext, it) }
            goToHome()
        }
    }
    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(){
        binding.loading.visibility = View.VISIBLE
    }
    private fun stopLoading(){
        binding.loading.visibility = View.GONE
    }
    private fun processError(msg: String?) {
        showToast("Error is:$msg")
    }
    private fun goToHome() {
        val intent = Intent(mContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}