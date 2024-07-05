package com.example.scooby.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentCheckoutPaymentBinding
import com.example.scooby.databinding.FragmentOrderSuccessBinding

class OrderSuccessFragment : Fragment() {
    private var _binding: FragmentOrderSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderSuccessBinding.inflate(layoutInflater,container,false)
        callBackButton()
        return binding.root
    }

    private fun callBackButton() {
        binding.BackToHomeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_orderSuccessFragment_to_homeFragment)
        }
    }

}