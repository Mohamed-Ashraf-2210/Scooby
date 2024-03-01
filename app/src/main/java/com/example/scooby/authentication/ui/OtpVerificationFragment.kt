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
import androidx.navigation.fragment.navArgs
import com.example.scooby.authentication.data.BaseResponse
import com.example.scooby.authentication.data.model.CheckCodeResponse
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentOtpVerificationBinding
import com.example.scooby.utils.Constant

class OtpVerificationFragment : Fragment() {

    private var _binding: FragmentOtpVerificationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mContext: Context
    private var flag = false
    private val args: OtpVerificationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtpVerificationBinding.inflate(inflater,container,false)
        mContext = requireContext()
        binding.tvEmail.text = args.email

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

        binding.btnSubmit.setOnClickListener {
            doCheck(it)
        }
        return binding.root
    }

    private fun processLogin(data: CheckCodeResponse?) {
        if (data != null) {
            Constant.ID_USER = data.userId
        }
    }

    private fun processError(msg: String?) {
        showToast("$msg Try again")
    }

    private fun doCheck(v: View) {
        viewModel.checkCode(binding.pinview.text.toString())
        if (flag) {
            val action = OtpVerificationFragmentDirections.actionOtpVerificationFragmentToResetPasswordFragment()
            Navigation.findNavController(v).navigate(action)
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