package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.rescue.PetsShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import com.example.scooby.databinding.FragmentRescueBinding
import com.example.scooby.scooby.paws.adapter.MarginBetweenDecoration
import com.example.scooby.scooby.paws.adapter.PetsShelterAdapter
import com.example.scooby.scooby.paws.adapter.ShelterAdapter
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel

class RescueFragment : Fragment() {
    private val pawsViewModel by viewModels<PawsViewModel>()
    private lateinit var binding: FragmentRescueBinding
    private lateinit var rvShelters: RecyclerView
    private lateinit var rvPetsShelter: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRescueBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        observeOfferViewModel()
        val recyclerView = binding.rvPetsInShelter
        val spaceDecoration = MarginBetweenDecoration(16)
        recyclerView.addItemDecoration(spaceDecoration)
    }

    private fun observeOfferViewModel() {
        pawsViewModel.apply {
            getShelters()
            shelterResult.observe(viewLifecycleOwner) {
                stopLoading()
                getShelterData(it)
                Log.d("SHELTER_RES", it.allShelters.toString())
            }
        }

        pawsViewModel.apply {
            getPetsShelters()
            petsShelterResult.observe(viewLifecycleOwner) { petsData ->
                stopLoading()
                getPetsShelterData(petsData)
            }
        }

    }

    private fun getShelterData(data: ShelterResponse) {
        rvShelters = binding.rvShelters
        rvShelters.adapter = ShelterAdapter(data)
    }

    private fun getPetsShelterData(data: PetsShelterResponse) {
        rvPetsShelter = binding.rvPetsInShelter
        rvPetsShelter.adapter = PetsShelterAdapter(data)
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
        }
    }
}