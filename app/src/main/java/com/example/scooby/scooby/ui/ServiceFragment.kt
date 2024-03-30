package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.databinding.FragmentServiceBinding
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.adapter.ServicesMainAdapter
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.viewmodel.ServicesViewModel
import com.example.scooby.utils.Constant

@SuppressLint("InflateParams")

class ServiceFragment : Fragment() {
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private lateinit var servicesRV: RecyclerView
    lateinit var servicesAdapter: ServicesAdapter
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
        callBackButton()
        observeViewModel()
    }

    private fun callBackButton() {

        binding.allBtn.setOnClickListener {
            servicesViewModel.getServicesFilter("sdsdf")

        }

    }


    private fun observeViewModel() {

        // Data For Services in home
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner){
                getServicesData(it)
            }

        }

        // Data For Services in ServicesFragment
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner){
                getServicesDataMain(it)
            }
        }

        servicesViewModel.servicesResultByFilter.observe(viewLifecycleOwner){
            it.let {
                servicesAdapter
            }
        }

    }
    private fun getServicesDataMain(data: ServicesResponse?) {
        servicesRV = binding.RvServicesContent
        servicesRV.adapter = ServicesMainAdapter(data!!, requireContext())
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