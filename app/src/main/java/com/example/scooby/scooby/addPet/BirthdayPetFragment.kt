package com.example.scooby.scooby.addPet

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentBirthdayPetBinding
import com.example.scooby.scooby.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * 4th screen to Add Pet
 * Add user pet birthday and adoption date
 * author: Mohamed Ashraf
 * */
class BirthdayPetFragment : Fragment() {
    private var binding: FragmentBirthdayPetBinding? = null
    private val args: BirthdayPetFragmentArgs by navArgs()
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) {
            return binding?.root
        }

        binding = FragmentBirthdayPetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        clickToSelectBirthday()
        clickToSelectAdoptionDate()
        clickListeners()
    }

    private fun clickListeners() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
    }


    private fun onClickNext() {
        binding?.apply {
            val listOfData = arrayOf(
                args.listOfData[0],
                args.listOfData[1],
                args.listOfData[2],
                args.listOfData[3],
                args.listOfData[4],
                selectDateTv.toString(),
                selectAdoptionDateTv.toString()
            )
            val action =
                BirthdayPetFragmentDirections.actionBirthdayPetFragmentToInfoPetFragment(
                    listOfData
                )
            findNavController().navigate(action)
        }
    }

    private fun clickToSelectAdoptionDate() {
        binding?.selectAdoptionDateTv?.setOnClickListener {
            showDatePickerAdoptionDate()
        }
    }

    private fun clickToSelectBirthday() {
        binding?.selectDateTv?.setOnClickListener {
            showDatePickerSelectDate()
        }
    }


    private fun showDatePickerSelectDate() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding?.selectDateTv?.text = formattedDate.toString()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun showDatePickerAdoptionDate() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding?.selectAdoptionDateTv?.text = formattedDate.toString()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
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