package com.example.scooby.scooby.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.FragmentDoctorsBinding
import com.example.scooby.scooby.adapter.DoctorAdapter
import com.example.scooby.scooby.data.model.DoctorsResponse
import com.example.scooby.scooby.viewmodel.VetViewModel


class DoctorsFragment : Fragment() {
    private lateinit var binding: FragmentDoctorsBinding
    private val doctorViewModel by viewModels<VetViewModel>()
    private lateinit var doctorRV: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshDoctorLayout.apply {
            setOnRefreshListener {
                init()
                isRefreshing = false
            }
        }
    }

    private fun init() {
        observeViewModel()
        backOffFragment()
    }

    private fun observeViewModel() {
        doctorViewModel.apply {
            getDoctors()
            doctorResult.observe(viewLifecycleOwner) {
                getDoctorsData(it)
            }
        }
    }

    private fun getDoctorsData(it: DoctorsResponse?) {
        doctorRV = binding.doctorCardRv
        doctorRV.adapter = DoctorAdapter(it!!, requireContext())
    }

    private fun backOffFragment() {
        binding.backDoctorIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.doctorCardRv.adapter = null
    }

}