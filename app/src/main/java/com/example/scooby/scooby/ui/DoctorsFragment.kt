package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.doctors.DoctorsResponse
import com.example.scooby.databinding.FragmentDoctorsBinding
import com.example.scooby.scooby.adapter.DoctorAdapter
import com.example.scooby.scooby.vet.viewModel.VetViewModel


class DoctorsFragment : Fragment() {
    private var binding: FragmentDoctorsBinding? = null
    private val doctorViewModel by viewModels<VetViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        init()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.refreshDoctorLayout?.apply {
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
        binding?.doctorCardRv?.adapter = DoctorAdapter(it!!, requireContext())
    }

    private fun backOffFragment() {
        binding?.backDoctorIcon?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            doctorContent.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.doctorCardRv?.adapter = null
    }

}