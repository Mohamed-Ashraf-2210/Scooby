package com.example.scooby.scooby.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBookingBinding
import com.example.scooby.databinding.FragmentUserProfileMomentBinding
import com.example.scooby.scooby.viewModels.CommunityViewModel
import com.example.scooby.scooby.viewModels.ProfileViewModel


class BookingFragment : Fragment() {
    private lateinit var communityViewModel: CommunityViewModel
    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(layoutInflater,container,false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
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
            initData()
      }
        binding.btnUpcoming.setOnClickListener {
            binding.btnPast.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white_btn_booking))
            binding.btnPast.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary))
            binding.btnUpcoming.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
        }
    }

    private fun initData() {

    }
}