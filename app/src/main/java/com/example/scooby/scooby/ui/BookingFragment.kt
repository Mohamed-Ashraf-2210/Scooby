package com.example.scooby.scooby.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBookingBinding


class BookingFragment : Fragment() {
    private lateinit var binding : FragmentBookingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        init()
        return binding.root
    }

    private fun init() {
        binding.btnPast.setOnClickListener {
            binding.btnPast.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary))
            binding.btnPast.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white_btn_booking))
            binding.btnUpcoming.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
      }
        binding.btnUpcoming.setOnClickListener {
            binding.btnPast.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white_btn_booking))
            binding.btnPast.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary))
            binding.btnUpcoming.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
        }
    }
}