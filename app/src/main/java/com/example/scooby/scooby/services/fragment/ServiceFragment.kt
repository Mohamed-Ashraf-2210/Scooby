package com.example.scooby.scooby.services.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.services.ServicesResponse
import com.example.scooby.databinding.FragmentServiceBinding
import com.example.scooby.scooby.services.adapter.ServicesMainAdapter
import com.example.scooby.scooby.services.viewmodel.ServicesViewModel

@SuppressLint("InflateParams")

class ServiceFragment : Fragment() {
    private lateinit var allServices: ServicesResponse
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private lateinit var servicesRV: RecyclerView

    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshServicesLayout.apply {
            setOnRefreshListener {
                init()
                isRefreshing = false
            }
        }
    }

    private fun init() {
//        callBackButton()
        backOffFragment()
        observeViewModel()
        callBackButton2()
    }

    private fun callBackButton2() {
        binding.allBtn.setOnClickListener {
            servicesViewModel.servicesResult.observe(viewLifecycleOwner) {
                getServicesDataMain(it)
            }
        }
//        binding.btnVet.setOnClickListener { filterServices("Hotel") }
//        binding.btnBoarding.setOnClickListener { filterServices("Boarding") }
//        binding.btnGrooming.setOnClickListener { filterServices("Grooming") }
//        binding.btnTraining.setOnClickListener { filterServices("Training") }
//        binding.btnWalking.setOnClickListener { filterServices("Walking") }
    }

//    private fun filterServices(serviceType: String) {
//        servicesViewModel.apply {
//            val filteredServices = allServices.shuffledServices.filter { it.serviceType == serviceType }
//            getServicesDataMain(ServicesResponse(filteredServices))
//        }
//    }


    // Here we Filter The Services Fragment by type of selected services


    private fun observeViewModel() {

        // Data For Services in ServicesFragment
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner) {
                stopLoading()
                getServicesDataMain(it)
                allServices = it!!
            }

        }
    }

    private fun getServicesDataMain(data: ServicesResponse?) {
        servicesRV = binding.RvServicesContent
        servicesRV.adapter = ServicesMainAdapter(data!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.RvServicesContent.adapter = null
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
        binding.RvServicesContent.visibility = View.VISIBLE
    }
    private fun backOffFragment() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}