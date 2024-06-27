package com.example.scooby.authentication.signin.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.authentication.viewModels.AuthViewModel
import com.example.scooby.databinding.FragmentResetPasswordBinding
import com.example.scooby.utils.BaseResponse
import kotlin.concurrent.thread


class ResetPasswordFragment : Fragment() {
    private var binding: FragmentResetPasswordBinding? = null
    private lateinit var viewModel: AuthViewModel
    private val args: ResetPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        authViewModelObserve()
        binding?.apply {
            btnLogin.setOnClickListener {
                val newPassword = editTextCreateNewPass.editText?.text.toString()
                val confirmPassword = editTextConfirmNewPass.editText?.text.toString()
                if (newPassword == confirmPassword) {
                    viewModel.resetPassword(args.userId, newPassword, newPassword)
                } else {
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding?.root
    }

    private fun authViewModelObserve() {
        viewModel.resetPasswordResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    resetSuccess()
                }

                is BaseResponse.Error -> {
                    stopLoading()
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun resetSuccess() {
        binding?.success?.visibility = View.VISIBLE
        thread {
            Thread.sleep(2000)
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
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