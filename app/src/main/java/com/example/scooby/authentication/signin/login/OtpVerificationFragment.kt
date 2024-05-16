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
import com.example.domain.authentication.CheckCodeResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentOtpVerificationBinding
import com.example.scooby.utils.BaseResponse

class OtpVerificationFragment : Fragment() {
    private var binding: FragmentOtpVerificationBinding? = null
    private lateinit var viewModel: AuthViewModel
    private val args: OtpVerificationFragmentArgs by navArgs()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpVerificationBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        authViewModelObserve()
        binding?.apply {
            tvEmail.text = args.email
            btnSubmit.setOnClickListener {
                viewModel.checkCode(binding?.pinview?.text.toString())
            }
        }
        return binding?.root
    }

    private fun authViewModelObserve() {
        viewModel.checkCodeResult.observe(viewLifecycleOwner) {
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

    private fun processLogin(data: CheckCodeResponse?) {
        if (data != null) {
            userId = data.userId
            val action =
                OtpVerificationFragmentDirections.actionOtpVerificationFragmentToResetPasswordFragment(
                    userId
                )
            findNavController().navigate(action)
        }
    }

    private fun processError(msg: String?) {
        Toast.makeText(context, "$msg Try again", Toast.LENGTH_SHORT).show()
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