package com.example.scooby.scooby.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.scooby.databinding.FragmentDoctorsBinding
import com.example.scooby.scooby.adapter.DoctorAdapter
import com.example.scooby.scooby.viewmodel.VetViewModel
import com.example.scooby.utils.BaseResponse


class DoctorsFragment : Fragment() {
    private var binding: FragmentDoctorsBinding? = null
    private lateinit var doctorViewModel: VetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) return binding?.root

        binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        doctorViewModel = ViewModelProvider(this)[VetViewModel::class.java]
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
        clickListener()
    }

    private fun observeViewModel() {
        doctorViewModel.apply {
            getDoctors()
            doctorResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        binding?.loading?.visibility = View.VISIBLE
                    }

                    is BaseResponse.Success -> {
                        if (it.data != null) {
                            binding?.doctorCardRv?.adapter =
                                DoctorAdapter(it.data, requireContext())
                        }
                        stopLoading()
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                    }

                }
            }
        }
    }

    private fun clickListener() {
        binding?.backScreen?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.doctorCardRv?.adapter = null
    }

}