package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.offer.OfferResponse
import com.example.domain.vet.VetResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentVetBinding
import com.example.scooby.scooby.adapter.VetAdapter
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.VetViewModel


class VetFragment : Fragment() {
    private lateinit var binding: FragmentVetBinding
    private val vetViewModel by viewModels<VetViewModel>()
    private val offerViewModel by viewModels<OfferViewModel>()
    private lateinit var vetRV: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVetBinding.inflate(inflater, container, false)
        init()
        observeOfferViewModel()
        return binding.root
    }

    private fun init() {
        observeViewModel()
        backOffFragment()
        btnBookDoctor()
        btnPharmacy()
    }

    private fun btnPharmacy() {
        binding.btnPharmacy.setOnClickListener {
            findNavController().navigate(R.id.action_vetFragment_to_medicineFragment)
        }
    }

    private fun btnBookDoctor() {
        binding.btnBookDoctorVet.setOnClickListener {
            findNavController().navigate(R.id.action_vetFragment_to_doctorsFragment)
        }
    }

    private fun observeViewModel() {
        vetViewModel.apply {
            getVet()
            vetResult.observe(viewLifecycleOwner) {
                getVetData(it)
            }
        }

    }

    private fun observeOfferViewModel() {
        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) {
                getOneOfferData(it)
            }
        }
    }

    private fun getOneOfferData(it: OfferResponse?) {
        val sizeOfList = it?.data?.size
        val randomNumber = (0..<sizeOfList!!).random()
        Glide.with(this).load(it.data[randomNumber].offerImage)
            .transform(CenterCrop(), RoundedCorners(24)).into(binding.offerVetImage)
    }

    private fun getVetData(it: VetResponse?) {
        vetRV = binding.veterinaryCardRv
        vetRV.adapter = VetAdapter(it!!, requireContext())
    }

    private fun backOffFragment() {
        binding.backVetIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshVetLayout.apply {
            setOnRefreshListener {
                init()
                isRefreshing = false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.veterinaryCardRv.adapter = null
    }
}
