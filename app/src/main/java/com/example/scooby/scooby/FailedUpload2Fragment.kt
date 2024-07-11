package com.example.scooby.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentFailedUpload2Binding
import com.example.scooby.databinding.FragmentFailedUploadBinding

class FailedUpload2Fragment : Fragment() {
    private var _binding: FragmentFailedUpload2Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFailedUpload2Binding.inflate(layoutInflater,container,false)
        callBackButton()
        return binding.root
    }

    private fun callBackButton() {
        binding.uploadBtn.setOnClickListener {
            findNavController().navigate(R.id.action_failedUpload2Fragment_to_createPostFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }


}