package com.example.scooby.scooby.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentNamePetBinding
import com.example.scooby.scooby.MainActivity


class NamePetFragment : Fragment() {
    private var binding: FragmentNamePetBinding? = null
    private var typePet: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamePetBinding.inflate(inflater, container, false)
        selectType()

        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
        return binding?.root
    }


    private fun selectType() {
        binding?.apply {
            catCard.setOnClickListener {
                if (typePet != "cat") {
                    typePet = "cat"
                    catView.setBackgroundResource(R.color.magenta)
                    dogView.setBackgroundResource(R.color.white_BG)
                } else {
                    typePet = null
                    catView.setBackgroundResource(R.color.white_BG)
                }
            }
            dogCard.setOnClickListener {
                if (typePet != "dog") {
                    typePet = "dog"
                    dogView.setBackgroundResource(R.color.magenta)
                    catView.setBackgroundResource(R.color.white_BG)
                } else {
                    typePet = null
                    dogView.setBackgroundResource(R.color.white_BG)
                }
            }
        }
    }


    private fun onClickNext() {
        binding?.apply {
            nextBtn.setOnClickListener {
                val petName = namePetEditText.text.toString()
                val petBreed = breedPetEditText.text.toString()
                if (petName.isNotEmpty() && petBreed.isNotEmpty() && typePet != null) {
                    val listOfData = arrayOf(
                        petName,
                        typePet!!,
                        petBreed
                    )
                    val action =
                        NamePetFragmentDirections.actionAddPetsFragmentToSizePetFragment(listOfData)
                    findNavController().navigate(action)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}