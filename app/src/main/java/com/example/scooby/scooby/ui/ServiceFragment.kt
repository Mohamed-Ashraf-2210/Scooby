package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.FragmentServiceBinding
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.adapter.ServicesMainAdapter
import com.example.domain.services.ServicesResponse
import com.example.scooby.scooby.viewmodel.ServicesViewModel
import java.util.Locale.filter

@SuppressLint("InflateParams")

class ServiceFragment : Fragment() {
    lateinit var allServices : ServicesResponse
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private lateinit var servicesRV: RecyclerView
    private lateinit var servicesMainAdapter: ServicesMainAdapter
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
            servicesViewModel.apply {
                val filterdServices = allServices.allServices.filter{
                    it.serviceType == "Boarding"
                }
                getServicesDataMain(ServicesResponse(filterdServices))
            }
        }

    }


    private fun observeViewModel() {

        // Data For Services in home
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner){
                getServicesDataMain(it)
                allServices = it!!
            }

        }
        // Data For Services in ServicesFragment
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner){
                getServicesDataMain(it)
            }
        }
    }
    private fun getServicesDataMain(data: ServicesResponse?) {
        servicesRV = binding.RvServicesContent
        servicesRV.adapter = ServicesMainAdapter(data!!, requireContext())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.RvServicesContent.adapter = null
    }

}