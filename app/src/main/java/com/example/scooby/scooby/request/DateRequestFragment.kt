package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.Constant
import com.example.scooby.databinding.FragmentDateRequestBinding
import com.example.scooby.scooby.MainActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DateRequestFragment : Fragment() {
    private var binding: FragmentDateRequestBinding? = null
    private val args: DateRequestFragmentArgs by navArgs()
    private val calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDateRequestBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }


    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
            exitSection.setOnClickListener { findNavController().popBackStack() }
            selectDateTv.setOnClickListener {
                showDatePickerSelectDate()
            }
            selectTimeTv.setOnClickListener {
                showTimePickerSelectDate()
            }
        }
    }

    private fun onClickNext() {
        binding?.apply {
            if (selectDateTv.text != "Select Date" && selectTimeTv.text != "Select Time") {
                val listOfData = arrayOf(
                    args.requestName,
                    selectDateTv.text.toString(),
                    selectTimeTv.text.toString()
                )
                Log.e(Constant.MY_TAG, listOfData.joinToString())
                val action =
                    DateRequestFragmentDirections.actionDateRequestFragmentToDurationRequestFragment(
                        args.idPets,
                        listOfData
                    )
                findNavController().navigate(action)
            } else if (selectDateTv.text == "Select Date") {
                Toast.makeText(requireContext(), "Not selected date yet", Toast.LENGTH_LONG)
                    .show()
            } else if (selectTimeTv.text == "Select Time") {
                Toast.makeText(requireContext(), "Not selected time yet", Toast.LENGTH_LONG)
                    .show()
            }
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

    @SuppressLint("SimpleDateFormat")
    private fun showTimePickerSelectDate() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            binding?.selectTimeTv?.text = SimpleDateFormat("HH:mm").format(calendar.time).toString()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
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