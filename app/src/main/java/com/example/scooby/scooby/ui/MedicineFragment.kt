package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.domain.offer.OfferResponse
import com.example.scooby.databinding.FragmentMedicineBinding
import com.example.scooby.scooby.viewmodel.OfferViewModel

class MedicineFragment : Fragment() {
    private val offerViewModel by viewModels<OfferViewModel>()

    private var _binding: FragmentMedicineBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicineBinding.inflate(layoutInflater, container, false)
        init()
        observeOfferViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshMedicineLayout.apply {
            setOnRefreshListener {
                isRefreshing = false
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

    private fun init() {

    }


    private fun getOneOfferData(it: OfferResponse?) {
        val sizeOfList = it?.data?.size
        val randomNumber = (0..<sizeOfList!!).random()
        Glide.with(this).load(it.data[randomNumber].offerImage).into(binding.offerMedImage)
    }


}