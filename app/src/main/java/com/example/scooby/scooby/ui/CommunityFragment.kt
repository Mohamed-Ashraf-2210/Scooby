package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.scooby.R
import com.example.scooby.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {
    private var binding: FragmentCommunityBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        binding?.apply {
            switchOnOff.setOnCheckedChangeListener { _, checked ->
                when {
                    checked -> {
                        publicBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
                        myMomentsBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                    }
                    else -> {
                        publicBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        myMomentsBtn.setTextColor(ContextCompat.getColor(requireContext(),R.color.primary))
                    }
                }
            }
        }
        return binding?.root
    }
}