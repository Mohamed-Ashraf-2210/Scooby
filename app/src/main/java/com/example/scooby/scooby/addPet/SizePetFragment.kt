package com.example.scooby.scooby.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentSizePetBinding
import com.example.scooby.scooby.MainActivity


class SizePetFragment : Fragment() {
    private var binding: FragmentSizePetBinding? = null
    private val args: SizePetFragmentArgs by navArgs()
    private var sizePet: String? = null
    private var genderPet: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSizePetBinding.inflate(inflater, container, false)
        selectSize()
        selectGender()

        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
        return binding?.root
    }


    private fun selectSize() {
        binding?.apply {
            fiveKgCard.setOnClickListener {
                if (sizePet != "1-5kg") {
                    sizePet = "1-5kg"
                    fiveKgView.setBackgroundResource(R.color.magenta)
                    tenKgView.setBackgroundResource(R.color.app_background)
                    twentyKgView.setBackgroundResource(R.color.app_background)
                    twentyPlusKgView.setBackgroundResource(R.color.app_background)
                } else {
                    sizePet = null
                    fiveKgView.setBackgroundResource(R.color.app_background)
                }
            }
            tenKgCard.setOnClickListener {
                if (sizePet != "6-10kg") {
                    sizePet = "6-10kg"
                    tenKgView.setBackgroundResource(R.color.magenta)
                    fiveKgView.setBackgroundResource(R.color.app_background)
                    twentyKgView.setBackgroundResource(R.color.app_background)
                    twentyPlusKgView.setBackgroundResource(R.color.app_background)
                } else {
                    sizePet = null
                    tenKgView.setBackgroundResource(R.color.app_background)
                }
            }
            twentyKgCard.setOnClickListener {
                if (sizePet != "11-20kg") {
                    sizePet = "11-20kg"
                    fiveKgView.setBackgroundResource(R.color.app_background)
                    tenKgView.setBackgroundResource(R.color.app_background)
                    twentyKgView.setBackgroundResource(R.color.magenta)
                    twentyPlusKgView.setBackgroundResource(R.color.app_background)
                } else {
                    sizePet = null
                    twentyKgView.setBackgroundResource(R.color.app_background)
                }
            }
            twentyPlusKgCard.setOnClickListener {
                if (sizePet != "20+kg") {
                    sizePet = "20+kg"
                    fiveKgView.setBackgroundResource(R.color.app_background)
                    tenKgView.setBackgroundResource(R.color.app_background)
                    twentyKgView.setBackgroundResource(R.color.app_background)
                    twentyPlusKgView.setBackgroundResource(R.color.magenta)
                } else {
                    sizePet = null
                    twentyPlusKgView.setBackgroundResource(R.color.app_background)
                }
            }
        }
    }

    private fun selectGender() {
        binding?.apply {
            maleCard.setOnClickListener {
                if (genderPet != "male") {
                    genderPet = "male"
                    maleView.setBackgroundResource(R.color.magenta)
                    femaleView.setBackgroundResource(R.color.app_background)
                    othersView.setBackgroundResource(R.color.app_background)
                } else {
                    sizePet = null
                    maleView.setBackgroundResource(R.color.app_background)
                }
            }
            femaleCard.setOnClickListener {
                if (genderPet != "female") {
                    genderPet = "female"
                    maleView.setBackgroundResource(R.color.app_background)
                    femaleView.setBackgroundResource(R.color.magenta)
                    othersView.setBackgroundResource(R.color.app_background)
                } else {
                    sizePet = null
                    femaleView.setBackgroundResource(R.color.app_background)
                }
            }
            othersCard.setOnClickListener {
                if (genderPet != "other") {
                    genderPet = "other"
                    maleView.setBackgroundResource(R.color.app_background)
                    femaleView.setBackgroundResource(R.color.app_background)
                    othersView.setBackgroundResource(R.color.magenta)
                } else {
                    sizePet = null
                    othersView.setBackgroundResource(R.color.app_background)
                }
            }
        }
    }

    private fun onClickNext() {
        binding?.apply {
            nextBtn.setOnClickListener {
                if (sizePet != null && genderPet != null) {
                    val listOfData = arrayOf(
                        args.listOfData[0],
                        args.listOfData[1],
                        args.listOfData[2],
                        sizePet!!,
                        genderPet!!
                    )
                    val action =
                        SizePetFragmentDirections.actionSizePetFragmentToBirthdayPetFragment(
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