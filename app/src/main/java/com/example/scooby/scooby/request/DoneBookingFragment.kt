package com.example.scooby.scooby.request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scooby.databinding.FragmentDoneBookingBinding
import com.example.scooby.scooby.MainActivity

class DoneBookingFragment : Fragment() {
    private var binding: FragmentDoneBookingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneBookingBinding.inflate(inflater,container,false)
        binding?.apply {
            BackToHomeBtn.setOnClickListener { findNavController().popBackStack() }
        }
        return binding?.root
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