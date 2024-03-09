package com.example.scooby.authentication.ui

import android.content.Context
import android.content.Intent
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
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.utils.TokenManager
import com.example.scooby.utils.BaseResponse
import com.example.scooby.authentication.data.model.UserResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentSignupBinding
import com.example.scooby.utils.Constant


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
            val name = checkName()
            val email = checkEmail()
            val pass = checkPassword()
            if (name && email && pass)
                doSignUp()
        }

        binding.tvSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_loginFragment)
        }

        return binding.root
    }

    // region Checking if field is okay
    private fun checkName(): Boolean {
        val name = binding.nameTextField.editText?.text.toString()
        if (name.isEmpty()) {
            binding.tvMsgError.visibility = View.VISIBLE
            binding.nameTextField.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
            return false
        } else {
            binding.tvMsgError.visibility = View.GONE
            binding.nameTextField.apply {
                boxStrokeColor = Color.argb(255,81,57,115)
                hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
            }
        }
        return true
    }
    private fun checkEmail(): Boolean {
        val email = binding.emailTextField.editText?.text.toString()
        if (email.isEmpty() || !isEmailValid(email)) {
            binding.msgErrorEmailOne.visibility = View.VISIBLE
            binding.msgErrorEmailTwo.visibility = View.VISIBLE
            binding.emailTextField.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
            return false
        } else {
            binding.msgErrorEmailOne.visibility = View.GONE
            binding.msgErrorEmailTwo.visibility = View.GONE
            binding.emailTextField.apply {
                boxStrokeColor = Color.argb(255,81,57,115)
                hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
            }
        }
        return true
    }
    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
    private fun checkPassword(): Boolean {
        val password = binding.passwordTextField.editText?.text.toString()
        if (password.length < 8) {
            binding.msgErrorPass.visibility = View.VISIBLE
            binding.passwordTextField.apply {
                boxStrokeColor = Color.RED
                hintTextColor = ColorStateList.valueOf(Color.RED)
            }
            return false
        } else {
            binding.msgErrorPass.visibility = View.GONE
            binding.passwordTextField.apply {
                boxStrokeColor = Color.argb(255,81,57,115)
                hintTextColor = ColorStateList.valueOf(Color.argb(255,81,57,115))
            }
        }
        return true
    }
    // endregion


    private fun doSignUp() {
        val email = binding.emailTextField.editText?.text.toString()
        val name = binding.nameTextField.editText?.text.toString()
        val password = binding.passwordTextField.editText?.text.toString()
        viewModel.signUpUser(email,name,password)
    }
    private fun processLogin(data: UserResponse?) {
        showToast("Welcome: " + (data?.data?.result?.name ?: ""))
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let { TokenManager.saveAuth(this.mContext,Constant.USER_TOKEN, it) }
            TokenManager.saveAuth(this.mContext,Constant.USER_NAME, data?.data?.result?.name ?: "")
            TokenManager.saveAuth(this.mContext,Constant.USER_ID, data?.data?.result?.id ?: "")
            goToHome()
        }
    }
    private fun goToHome() {
        val intent = Intent(mContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }


    // region Loading
    private fun showLoading(){
        binding.loading.visibility = View.VISIBLE
    }
    private fun stopLoading(){
        binding.loading.visibility = View.GONE
    }
    // endregion

    private fun processError(msg: String?) {
//        showToast("Error is:$msg")
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}