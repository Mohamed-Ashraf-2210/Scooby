package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.scooby.R
import com.example.scooby.databinding.FragmentVetBinding
import com.example.scooby.scooby.adapter.VetAdapter
import com.example.scooby.scooby.data.model.OfferResponse
import com.example.scooby.scooby.data.model.VetResponse
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.VetViewModel
import kotlin.random.Random
import kotlin.random.nextInt


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
        binding.btnBookDoctorVet.setOnClickListener {
            findNavController().navigate(R.id.action_vetFragment_to_doctorsFragment)
        }
        init()
        return binding.root
    }

    private fun init() {
        observeViewModel()
        backOffFragment()
    }

    private fun observeViewModel() {
        vetViewModel.apply {
            getVet()
            vetResult.observe(viewLifecycleOwner) {
                getvetData(it)
            }
        }
        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) {
                getOfferData(it)
            }
        }
    }

    private fun getOfferData(it: OfferResponse?) {
        val sizeOfList = it?.data?.size
        val randomNumber = (0..<sizeOfList!!).random()
        binding.offerVetImage.setImageURI(it.data[randomNumber].offerImage.toUri())
    }

    private fun getvetData(it: VetResponse?) {
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

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.veterinaryCardRv.adapter = null
    }
}