package com.example.scooby.scooby.vet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.offer.OfferResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentVetBinding
import com.example.scooby.scooby.adapter.VetAdapter
import com.example.scooby.scooby.viewmodel.VetViewModel
import com.example.scooby.scooby.viewmodel.OfferViewModel

class VetFragment : Fragment() {

    private lateinit var binding: FragmentVetBinding
    private val vetViewModel by viewModels<VetViewModel>()
    private val offerViewModel by viewModels<OfferViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVetBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        setupNavigation()
        setupVetData()
        setupOfferData()
    }


    private fun setupNavigation() {
        binding.backVetIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnBookDoctorVet.setOnClickListener {
            findNavController().navigate(R.id.action_vetFragment_to_doctorsFragment)
        }
        binding.btnPharmacy.setOnClickListener {
            findNavController().navigate(R.id.action_vetFragment_to_productFragment)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshVetLayout.apply {
            setOnRefreshListener {
                initView()
                isRefreshing = false
            }
        }
    }

    private fun setupVetData() {
        vetViewModel.apply {
            getVet()
            vetResult.observe(viewLifecycleOwner) { vet ->
                if (vet != null) {
                    binding.veterinaryCardRv.adapter = VetAdapter(vet, requireContext())
                    binding.veterinaryCardRv.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    private fun setupOfferData() {
        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) { offerResponse ->
                if (offerResponse != null) {
//                    val filteredOffers = offerResponse.data.filter { it.type == "medicine" }
//                    val medicineOffer = OfferResponse(filteredOffers, 1)
//                    displayOfferImage(medicineOffer)
                }
            }
        }
    }

    private fun displayOfferImage(offerResponse: OfferResponse) {
        val offer = offerResponse.data.firstOrNull()
        if (offer != null) {
            Glide.with(this)
                .load(offer.offerImage)
                .transform(CenterCrop(), RoundedCorners(50))
                .into(binding.offerVetImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.veterinaryCardRv.adapter = null
    }
}
