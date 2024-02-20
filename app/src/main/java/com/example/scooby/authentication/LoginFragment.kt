package com.example.scooby.authentication

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.viewModels
import com.example.scooby.MainActivity
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginPageBinding
import com.example.scooby.utils.Constant


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginPageBinding.inflate(inflater,container,false)
        mContext = requireContext()
        val token = TokenManager.getToken(mContext)
        if (!token.isNullOrBlank()) {
            goToHome()
        }
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
                    processError(it.msg)
                    Log.i("MSG_ERORR",it.msg.toString())
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        // Inflate the layout for this fragment
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
            data?.token?.let { TokenManager.saveAuthToken(mContext, it) }
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
        showToast("Error:$msg")
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