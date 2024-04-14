package com.example.scooby.scooby.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentBreedPetBinding
import com.example.scooby.scooby.MainActivity


class BreedPetFragment : Fragment() {
    private var binding: FragmentBreedPetBinding? = null
    private val args: BreedPetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedPetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
    }

    private fun onClickNext() {
        binding?.apply {
            nextBtn.setOnClickListener {
                val petBreed = breedPetEditText.text.toString()
                if (petBreed.isNotEmpty()) {
                    val listOfData = arrayOf(
                        args.listOfData[0],
                        args.listOfData[1],
                        petBreed
                    )
                    val action = BreedPetFragmentDirections.actionBreedFragmentToSizePetFragment(
                        listOfData
                    )
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