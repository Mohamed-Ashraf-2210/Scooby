package com.example.scooby.scooby.menuBottomSheet

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
                val requestName = arrayOf(
                    "Pet Boarding",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            sittingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Sitting",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            petFriendlyPlacesIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Friendly Places",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            groomingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Grooming",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            trainingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Training",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            suppliesIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Supplies",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            petTaxiIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Taxi",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
                    )
                findNavController().navigate(action)
            }
            walkingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Walking",
                    "20"
                )
                val action =
                    MenuBottomSheetFragmentDirections.actionMenuBottomSheetFragmentToSelectPetFragment(
                        requestName
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
