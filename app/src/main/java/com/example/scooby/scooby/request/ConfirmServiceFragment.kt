package com.example.scooby.scooby.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentConfirmServiceBinding


class ConfirmServiceFragment : Fragment() {
    private var binding: FragmentConfirmServiceBinding? = null
    private val args: ConfirmServiceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmServiceBinding.inflate(inflater, container, false)
        init()
        return binding?.root
    }

    private fun init() {

    }

}