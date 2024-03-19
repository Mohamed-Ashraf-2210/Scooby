package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.FragmentServiceBinding
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.viewmodel.ServicesViewModel

@SuppressLint("InflateParams")

class ServiceFragment : Fragment() {
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private lateinit var servicesRV: RecyclerView
    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            _binding = FragmentServiceBinding.inflate(inflater,container,false)
            init()
        return binding.root
    }

    private fun init() {

        observeViewModel()
    }

    private fun observeViewModel() {
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner){
                getServicesData(it)
            }

        }


        servicesViewModel.apply {
            getServicesByFilter("Hotel")
            servicesResultByFilter.observe(viewLifecycleOwner){
                getServicesByFilter("Hotel")
            }

        }
    }

    private fun getServicesData(data: ServicesResponse?) {
        servicesRV = binding.RvServicesContent
        servicesRV.adapter = ServicesAdapter(data!!, requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.RvServicesContent.adapter = null
    }

}