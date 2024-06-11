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

/**
 * 5th screen to Add Pet
 * Add user pet info
 * author: Mohamed Ashraf
 * */
class InfoPetFragment : Fragment() {
    private var binding: FragmentInfoPetBinding? = null
    private val args: InfoPetFragmentArgs by navArgs()
    private val info = BooleanArray(12) { false }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoPetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        setupClickListeners()
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
    }

    private fun setupClickListeners() {
        val clickableViews = arrayOf(
            binding?.vaccinatedCard, binding?.friendlyWithCatsCard, binding?.microCard,
            binding?.anxietyCard, binding?.hygieneCard, binding?.medicalCard,
            binding?.aggressiveCard, binding?.diseaseCard, binding?.SpayedCard,
            binding?.fullMedicalCard, binding?.friendlyWithDogsCard, binding?.friendlyWithKidsCard
        )

        clickableViews.forEachIndexed { index, view ->
            view?.setOnClickListener {
                toggleInfo(index)
                setBackgroundResource(index)
            }
        }
    }

    private fun toggleInfo(index: Int) {
        info[index] = !info[index]
    }

    private fun setBackgroundResource(index: Int) {
        val view = when (index) {
            0 -> binding?.vaccinatedView
            1 -> binding?.friendlyWithCatsView
            2 -> binding?.microView
            3 -> binding?.anxietyView
            4 -> binding?.hygieneView
            5 -> binding?.medicalView
            6 -> binding?.aggressiveView
            7 -> binding?.diseaseView
            8 -> binding?.SpayedView
            9 -> binding?.fullMedicalView
            10 -> binding?.friendlyWithDogsView
            11 -> binding?.friendlyWithKidsView
            else -> null
        }
        view?.setBackgroundResource(if (info[index]) R.color.magenta else R.color.app_background)
    }

    private fun onClickNext() {
        binding?.apply {
            val bio = info.indices.filter { info[it] }.joinToString(separator = ", ") {
                arrayOf(
                    "Vaccinated", "Friendly with cats", "Microchipped",
                    "Has any anxiety", "Has hygiene issues", "Full medical records",
                    "Aggressive", "Has any disease", "Spayed",
                    "Full medical records", "Friendly with dogs", "Friendly with kids"
                )[it]
            }
            val action = InfoPetFragmentDirections.actionInfoPetFragmentToSubmitPetFragment(
                args.listOfData?.plus(bio)
            )
            findNavController().navigate(action)
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