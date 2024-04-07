package com.example.scooby.scooby.userProfile.fragment.addPet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentInfoPetBinding
import com.example.scooby.scooby.MainActivity


class InfoPetFragment : Fragment() {
    private var binding: FragmentInfoPetBinding? = null
    private val args: InfoPetFragmentArgs by navArgs()
    private val booleanArray = BooleanArray(12) { false }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoPetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        clickToBack()
        clickToNext()
        selectInfo()
    }

    private fun clickToNext() {
        binding?.apply {
            nextBtn.setOnClickListener {

                val action = InfoPetFragmentDirections.actionInfoPetFragmentToSubmitPetFragment(
                    args.petName,
                    args.petType,
                    args.petBreed,
                    args.petSize,
                    args.petGender,
                    args.petBirthday,
                    args.petAdoption,
                    booleanArray
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun selectInfo() {
        binding?.apply {
            vaccinatedCard.setOnClickListener {
                if (!booleanArray[0]) {
                    booleanArray[0] = true
                    vaccinatedView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[0] = false
                    vaccinatedView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithCatsCard.setOnClickListener {
                if (!booleanArray[1]) {
                    booleanArray[1] = true
                    friendlyWithCatsView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[1] = false
                    friendlyWithCatsView.setBackgroundResource(R.color.app_background)
                }
            }
            microCard.setOnClickListener {
                if (!booleanArray[2]) {
                    booleanArray[2] = true
                    microView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[2] = false
                    microView.setBackgroundResource(R.color.app_background)
                }
            }
            anxietyCard.setOnClickListener {
                if (!booleanArray[3]) {
                    booleanArray[3] = true
                    anxietyView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[3] = false
                    anxietyView.setBackgroundResource(R.color.app_background)
                }
            }
            hygieneCard.setOnClickListener {
                if (!booleanArray[4]) {
                    booleanArray[4] = true
                    hygieneView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[4] = false
                    hygieneView.setBackgroundResource(R.color.app_background)
                }
            }
            medicalCard.setOnClickListener {
                if (!booleanArray[5]) {
                    booleanArray[5] = true
                    medicalView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[5] = false
                    medicalView.setBackgroundResource(R.color.app_background)
                }
            }

            aggressiveCard.setOnClickListener {
                if (!booleanArray[6]) {
                    booleanArray[6] = true
                    aggressiveView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[6] = false
                    aggressiveView.setBackgroundResource(R.color.app_background)
                }
            }
            diseaseCard.setOnClickListener {
                if (!booleanArray[7]) {
                    booleanArray[7] = true
                    diseaseView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[7] = false
                    diseaseView.setBackgroundResource(R.color.app_background)
                }
            }
            SpayedCard.setOnClickListener {
                if (!booleanArray[8]) {
                    booleanArray[8] = true
                    SpayedView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[8] = false
                    SpayedView.setBackgroundResource(R.color.app_background)
                }
            }
            fullMedicalCard.setOnClickListener {
                if (!booleanArray[9]) {
                    booleanArray[9] = true
                    fullMedicalView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[9] = false
                    fullMedicalView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithDogsCard.setOnClickListener {
                if (!booleanArray[10]) {
                    booleanArray[10] = true
                    friendlyWithDogsView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[10] = false
                    friendlyWithDogsView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithKidsCard.setOnClickListener {
                if (!booleanArray[11]) {
                    booleanArray[11] = true
                    friendlyWithKidsView.setBackgroundResource(R.color.magenta)
                } else {
                    booleanArray[11] = false
                    friendlyWithKidsView.setBackgroundResource(R.color.app_background)
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