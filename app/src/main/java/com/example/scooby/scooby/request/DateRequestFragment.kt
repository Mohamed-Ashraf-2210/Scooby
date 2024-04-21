package com.example.scooby.scooby.request

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.Constant
import com.example.scooby.R
import com.example.scooby.databinding.FragmentDateRequestBinding
import com.example.scooby.scooby.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DateRequestFragment : Fragment() {
    private var binding: FragmentDateRequestBinding? = null
    private val args: DateRequestFragmentArgs by navArgs()
    private val calendar = Calendar.getInstance()
    private var flag = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var location: String? = null

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
            selectDateTv.setOnClickListener {
                showDatePickerSelectDate()
            }
            selectTimeTv.setOnClickListener {
                showTimePickerSelectDate()
            }
            optionEt.setOnClickListener {
                showPopup(it)
            }
            locationTv.setOnClickListener { takeUserLocation() }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun showPopup(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.duration_request_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it!!.itemId) {
                R.id.full_day_item -> {
                    binding?.optionEt?.text = "Full Day"
                }

                R.id.half_day_item -> {
                    binding?.optionEt?.text = "Half Day"
                }

                R.id.more_than_day_item -> {
                    binding?.optionEt?.text = "More than 1 Day"
                }

                else -> {
                    flag = false
                }
            }
            flag = true
            true
        }
    }

    private fun takeUserLocation() {
        // Check for location permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permission
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                DateRequestFragment.LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, get location
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        // Get last known location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Handle location
                    val latitude = location.latitude
                    val longitude = location.longitude
                    this.location = "Latitude: $latitude, Longitude: $longitude"
                    Toast.makeText(
                        requireContext(),
                        this.location,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding?.locationTv?.text = location.provider
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Location is null. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener { e ->
                // Handle failure
                Toast.makeText(
                    requireContext(),
                    "Failed to get location: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == DateRequestFragment.LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission granted
                Toast.makeText(
                    requireContext(),
                    "Location permission granted.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Location permission denied.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onClickNext() {
        binding?.apply {
            if (selectDateTv.text != "Select Date" && selectTimeTv.text != "Select Time") {
                val listOfData = arrayOf(
                    selectDateTv.text.toString(),
                    selectTimeTv.text.toString(),
                    optionEt.text.toString(),
                    locationTv.text.toString()
                )
                Log.e(Constant.MY_TAG, listOfData.joinToString())
//                val action =
//                    DateRequestFragmentDirections.actionDateRequestFragmentToDurationRequestFragment(
//                        args.idPets,
//                        listOfData,
//                        args.requestName
//                    )
//                findNavController().navigate(action)
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

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

}