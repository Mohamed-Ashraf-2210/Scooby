package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentFailedUploadBinding
import com.example.scooby.databinding.FragmentMomentBinding

class FailedUploadFragment : Fragment() {
    private var _binding: FragmentFailedUploadBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFailedUploadBinding.inflate(layoutInflater,container,false)
        nav2AIFragment()
        return binding.root
    }

    private fun nav2AIFragment() {
        binding.uploadBtn.setOnClickListener {
            findNavController().navigate(R.id.action_failedUploadFragment_to_aiFragment)
        }
    }


}