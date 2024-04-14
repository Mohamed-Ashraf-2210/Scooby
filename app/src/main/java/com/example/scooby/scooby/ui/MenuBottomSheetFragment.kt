package com.example.scooby.scooby.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.MenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private var binding: MenuBottomSheetBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuBottomSheetBinding.inflate(inflater)
        iconClick()
        return binding?.root
    }

    private fun iconClick() {
        binding?.apply {
            vetIcon.setOnClickListener {
                findNavController().navigate(R.id.action_menuBottomSheetFragment_to_vetFragment)
            }
            boardingIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Boarding"
                    )
                findNavController().navigate(action)
            }
            sittingIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Sitting"
                    )
                findNavController().navigate(action)
            }
            petFriendlyPlacesIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Pet Friendly Places"
                    )
                findNavController().navigate(action)
            }
            groomingIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Grooming"
                    )
                findNavController().navigate(action)
            }
            trainingIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Training"
                    )
                findNavController().navigate(action)
            }
            suppliesIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Supplies"
                    )
                findNavController().navigate(action)
            }
            petTaxiIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Pet Taxi"
                    )
                findNavController().navigate(action)
            }
            walkingIcon.setOnClickListener {
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        "Walking"
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
