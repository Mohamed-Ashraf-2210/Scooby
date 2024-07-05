package com.example.scooby.scooby.booking

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBookingBinding
import com.example.scooby.scooby.viewModels.CommunityViewModel
import com.example.scooby.utils.BaseResponse


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
        communityViewModel.getUpcomingBooking()
        init()
        initData()
        return binding.root
    }

    private fun init() {
        binding.btnPast.setOnClickListener {
            binding.btnPast.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary))
            binding.btnPast.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white_btn_booking))
            binding.btnUpcoming.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            communityViewModel.getPastBooking()

      }
        binding.btnUpcoming.setOnClickListener {
            binding.btnPast.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white_btn_booking))
            binding.btnPast.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
            binding.btnUpcoming.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.primary))
            binding.btnUpcoming.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            communityViewModel.getUpcomingBooking()
        }
    }

    private fun initData() {
        observeUpcomingBook()
    }

    @SuppressLint("SetTextI18n")
    private fun observeUpcomingBook() {
        communityViewModel.apply {
            bookingUpcomingResult.observe(viewLifecycleOwner){
                when(it){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success ->{
                        stopLoading()
                        Log.i("InfoUpcoming",it.data.toString())
                        if (it.data?.request.isNullOrEmpty()){
                            binding.textview.visibility = View.VISIBLE
                            binding.rvBooking.visibility = View.GONE
                            binding.textview.text = "There are no booking requests yet..."
                        }else{
                            binding.textview.visibility = View.GONE
                            binding.rvBooking.visibility = View.VISIBLE
                            binding.rvBooking.adapter = it.data?.let { it1 -> UpBookAdapter(it1) }
                        }

                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast("error in booking Upcoming")

                    }
                    else -> {
                        stopLoading()
                    }
                }
            }

            bookingPastResult.observe(viewLifecycleOwner){
                when(it){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success ->{
                        stopLoading()
                        Log.i("InfoPast",it.data.toString())
                        if (it.data?.request.isNullOrEmpty()){
                            binding.textview.visibility = View.VISIBLE
                            binding.rvBooking.visibility = View.GONE
                            binding.textview.text = "There are no booking requests yet..."
                        }else{
                            binding.textview.visibility = View.GONE
                            binding.rvBooking.visibility = View.VISIBLE
                            binding.rvBooking.adapter = it.data?.let { it1 -> UpBookAdapter(it1) }
                        }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast("error in booking Upcoming")

                    }
                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }
    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
        binding.rvBooking.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }
}