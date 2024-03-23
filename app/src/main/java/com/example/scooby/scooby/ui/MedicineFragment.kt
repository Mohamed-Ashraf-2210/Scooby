package com.example.scooby.scooby.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.R
import com.example.scooby.databinding.FragmentMedicineBinding
import com.example.scooby.databinding.FragmentServiceBinding

class MedicineFragment : Fragment() {

    private var _binding: FragmentMedicineBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicineBinding.inflate(layoutInflater,container,false)
        makeStrikeOnText()
        return binding.root
    }

    private fun makeStrikeOnText() {
    }

}