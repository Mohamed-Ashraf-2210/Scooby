package com.example.scooby.scooby.vet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.offer.OfferResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentVetBinding
import com.example.scooby.scooby.adapter.VetAdapter
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.VetViewModel
import com.example.scooby.utils.BaseResponse

class VetFragment : Fragment() {

    private var binding: FragmentVetBinding? = null
    private lateinit var vetViewModel: VetViewModel
    private lateinit var offerViewModel: OfferViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVetBinding.inflate(inflater, container, false)
        vetViewModel = ViewModelProvider(this)[VetViewModel::class.java]
        offerViewModel = ViewModelProvider(this)[OfferViewModel::class.java]

        initView()
        return binding?.root
    }

    private fun initView() {
        setupNavigation()
        setupVetData()
        setupOfferData()
    }


    private fun setupNavigation() {
        binding?.apply {
            backVetIcon.setOnClickListener {
                findNavController().popBackStack()
            }
            btnBookDoctorVet.setOnClickListener {
                findNavController().navigate(R.id.action_vetFragment_to_doctorsFragment)
            }
            btnPharmacy.setOnClickListener {
                findNavController().navigate(R.id.action_vetFragment_to_productFragment)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.refreshVetLayout?.apply {
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
                when (vet) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()

                        if (vet.data != null) {
                            binding?.veterinaryCardRv?.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = VetAdapter(vet.data, requireContext())
                            }
                        }
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(vet.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun setupOfferData() {
        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) { offerResponse ->
                when (offerResponse) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()

                        if (offerResponse.data != null) {
                            val filteredOffers =
                                offerResponse.data.data.filter { it.type == "medicine" }
                            val medicineOffer = OfferResponse(filteredOffers, 1)
                            displayOfferImage(medicineOffer)
                        }
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(offerResponse.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


    private fun displayOfferImage(offerResponse: OfferResponse) {
        val offer = offerResponse.data.firstOrNull()
        if (offer != null) {
            binding?.let {
                Glide.with(this)
                    .load(offer.offerImage)
                    .transform(CenterCrop(), RoundedCorners(50))
                    .into(it.offerVetImage)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.veterinaryCardRv?.adapter = null
    }
}
