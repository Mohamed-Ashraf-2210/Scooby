package com.example.scooby.scooby.userProfile.fragment.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentSizePetBinding
import com.example.scooby.scooby.MainActivity


class SizePetFragment : Fragment() {
    private var binding: FragmentSizePetBinding? = null
    private val args: SizePetFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSizePetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        clickToNext()
        clickToBack()
    }

    private fun clickToNext() {
        binding?.apply {
            nextBtn.setOnClickListener {
//                val petName = args.petName
//                if (typePet != null) {
//                    val action = TypePetFragmentDirections.actionTypePetFragmentToBreedFragment(
//                        petName,
//                        typePet!!
//                    )
//                    findNavController().navigate(action)
//                }
//
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