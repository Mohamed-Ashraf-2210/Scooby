package com.example.scooby.scooby.userProfile.fragment.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.scooby.databinding.FragmentNamePetBinding
import com.example.scooby.scooby.MainActivity


class NamePetFragment : Fragment() {
    private var binding: FragmentNamePetBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNamePetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        clickToBack()
        clickToNext()
    }

    private fun clickToNext() {
        binding?.apply {
            nextBtn.setOnClickListener {
                val petName = namePetEditText.text.toString()
                if (petName.isNotEmpty()) {
                    val action =
                        NamePetFragmentDirections.actionAddPetsFragmentToTypePetFragment(petName)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun clickToBack() {
        binding?.backProfile?.setOnClickListener {
            findNavController().popBackStack()
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
}