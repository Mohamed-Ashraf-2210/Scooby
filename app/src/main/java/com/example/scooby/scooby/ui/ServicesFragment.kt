package com.example.scooby.scooby.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.R
import com.example.scooby.databinding.FragmentServicesBinding
import com.example.scooby.scooby.adapter.ServicesRvHorAdapter
import com.example.scooby.scooby.data.model.ServicesRvList

class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!
    private lateinit var servicesAdapter : ServicesRvHorAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(layoutInflater,container,false)
        initRv()
        return binding.root
    }

    private fun initRv() {
        val itemList = mutableListOf(
            ServicesRvList(R.drawable.pets,"All"),
            ServicesRvList(R.drawable.veterinarian,"vet"),
            ServicesRvList(R.drawable.world_animal,"Boarding"),
            ServicesRvList(R.drawable.grooming,"Grooming"),
            ServicesRvList(R.drawable.dog_training,"Training"),
            ServicesRvList(R.drawable.dog_walking,"Walking"),
            ServicesRvList(R.drawable.pet_taxi,"Taxi"),
            ServicesRvList(R.drawable.sitting_dog,"Sitting"),
            ServicesRvList(R.drawable.pet_supplies,"Supplies")
        )
        servicesAdapter = ServicesRvHorAdapter(itemList as ArrayList,requireContext())
        binding.RvServicesCircle.adapter = servicesAdapter
    }


}