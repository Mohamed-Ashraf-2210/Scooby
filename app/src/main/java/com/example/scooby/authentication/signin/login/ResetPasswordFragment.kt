package com.example.scooby.authentication.signin.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.scooby.authentication.viewmodel.AuthViewModel
import com.example.scooby.databinding.FragmentLoginBinding
import com.example.scooby.databinding.FragmentResetPasswordBinding


class ResetPasswordFragment : Fragment() {
    private var binding: FragmentResetPasswordBinding? = null
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        return binding?.root
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