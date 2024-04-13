package com.example.scooby.scooby.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentTypePetBinding
import com.example.scooby.scooby.MainActivity


class TypePetFragment : Fragment() {
    private var binding: FragmentTypePetBinding? = null
    private val args: TypePetFragmentArgs by navArgs()
    private var typePet: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypePetBinding.inflate(inflater, container, false)
        initView()
        selectType()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
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
                if (typePet != null) {
                    val listOfData = arrayOf(
                        args.petName,
                        typePet!!
                    )
                    val action = TypePetFragmentDirections.actionTypePetFragmentToBreedFragment(
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