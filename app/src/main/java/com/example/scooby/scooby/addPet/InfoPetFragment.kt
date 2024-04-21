package com.example.scooby.scooby.addPet

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
    private val info = BooleanArray(12) { false }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoPetBinding.inflate(inflater, container, false)
        selectInfo()
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
        return binding?.root
    }


    private fun onClickNext() {
        binding?.apply {
            nextBtn.setOnClickListener {

                var bio = ""
                val array = arrayOf(
                    "Vaccinated, ",
                    "Friendly with cats, ",
                    "Microchipped, ",
                    "Has any anxiety, ",
                    "Has hygiene issues, ",
                    "Full medical records, ",
                    "Aggressive, ",
                    "Has any disease, ",
                    "Spayed, ",
                    "Full medical records, ",
                    "Friendly with dogs, ",
                    "Friendly with kids, "
                )
                for (i in 0..11) {
                    if (info[i]) bio += array[i]
                }

                val action = InfoPetFragmentDirections.actionInfoPetFragmentToSubmitPetFragment(
                    args.listOfData?.plus(bio)
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun selectInfo() {
        binding?.apply {
            vaccinatedCard.setOnClickListener {
                if (!info[0]) {
                    info[0] = true
                    vaccinatedView.setBackgroundResource(R.color.magenta)
                } else {
                    info[0] = false
                    vaccinatedView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithCatsCard.setOnClickListener {
                if (!info[1]) {
                    info[1] = true
                    friendlyWithCatsView.setBackgroundResource(R.color.magenta)
                } else {
                    info[1] = false
                    friendlyWithCatsView.setBackgroundResource(R.color.app_background)
                }
            }
            microCard.setOnClickListener {
                if (!info[2]) {
                    info[2] = true
                    microView.setBackgroundResource(R.color.magenta)
                } else {
                    info[2] = false
                    microView.setBackgroundResource(R.color.app_background)
                }
            }
            anxietyCard.setOnClickListener {
                if (!info[3]) {
                    info[3] = true
                    anxietyView.setBackgroundResource(R.color.magenta)
                } else {
                    info[3] = false
                    anxietyView.setBackgroundResource(R.color.app_background)
                }
            }
            hygieneCard.setOnClickListener {
                if (!info[4]) {
                    info[4] = true
                    hygieneView.setBackgroundResource(R.color.magenta)
                } else {
                    info[4] = false
                    hygieneView.setBackgroundResource(R.color.app_background)
                }
            }
            medicalCard.setOnClickListener {
                if (!info[5]) {
                    info[5] = true
                    medicalView.setBackgroundResource(R.color.magenta)
                } else {
                    info[5] = false
                    medicalView.setBackgroundResource(R.color.app_background)
                }
            }

            aggressiveCard.setOnClickListener {
                if (!info[6]) {
                    info[6] = true
                    aggressiveView.setBackgroundResource(R.color.magenta)
                } else {
                    info[6] = false
                    aggressiveView.setBackgroundResource(R.color.app_background)
                }
            }
            diseaseCard.setOnClickListener {
                if (!info[7]) {
                    info[7] = true
                    diseaseView.setBackgroundResource(R.color.magenta)
                } else {
                    info[7] = false
                    diseaseView.setBackgroundResource(R.color.app_background)
                }
            }
            SpayedCard.setOnClickListener {
                if (!info[8]) {
                    info[8] = true
                    SpayedView.setBackgroundResource(R.color.magenta)
                } else {
                    info[8] = false
                    SpayedView.setBackgroundResource(R.color.app_background)
                }
            }
            fullMedicalCard.setOnClickListener {
                if (!info[9]) {
                    info[9] = true
                    fullMedicalView.setBackgroundResource(R.color.magenta)
                } else {
                    info[9] = false
                    fullMedicalView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithDogsCard.setOnClickListener {
                if (!info[10]) {
                    info[10] = true
                    friendlyWithDogsView.setBackgroundResource(R.color.magenta)
                } else {
                    info[10] = false
                    friendlyWithDogsView.setBackgroundResource(R.color.app_background)
                }
            }
            friendlyWithKidsCard.setOnClickListener {
                if (!info[11]) {
                    info[11] = true
                    friendlyWithKidsView.setBackgroundResource(R.color.magenta)
                } else {
                    info[11] = false
                    friendlyWithKidsView.setBackgroundResource(R.color.app_background)
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