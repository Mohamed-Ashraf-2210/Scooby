package com.example.scooby.scooby.request

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.Constant
import com.example.scooby.R
import com.example.scooby.databinding.FragmentDateRequestBinding
import com.example.scooby.scooby.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Second screen to Request a service
 * Select date, time, location and duration
 * author: Mohamed Ashraf
 * */
class DateRequestFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var binding: FragmentDateRequestBinding? = null
    private val args: DateRequestFragmentArgs by navArgs()
    private val calendar = Calendar.getInstance()
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var userLocation: String = ""
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDateRequestBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        initView()
        return binding?.root
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            selectDateTv.setOnClickListener {
                showDatePickerSelectDate()
            }
            selectTimeTv.setOnClickListener {
                showTimePickerSelectDate()
            }
            optionEt.setOnClickListener {
                showPopup(it)
            }
            locationTv.setOnClickListener {
                checkLocationPermission()
            }
            nextBtn.setOnClickListener { onClickNext() }
            nextBtn.text = "Next (\$${args.requestName[1]} /night)"
        }
    }

    // region Pick date
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

    // endregion
    // region Pick time
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

    // endregion
    // region Popup menu
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

    // endregion
    // region Location
    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults.isNotEmpty() &&
            grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        } else {
            Toast.makeText(requireActivity(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }


    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getCurrentLocation() {

        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        ) {
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                val location: Location? = task.result
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                    userLocation = getCityAndCountry()
                    binding?.locationTv?.text = userLocation
                } else {
                    val locationRequest = LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(10000)
                        .setFastestInterval(1000)
                        .setNumUpdates(1)

                    val locationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            val location1 = locationResult.lastLocation
                            if (location1 != null) {
                                latitude = location1.latitude
                            }
                            if (location1 != null) {
                                longitude = location1.longitude
                            }
                            userLocation = getCityAndCountry()
                            binding?.locationTv?.text = userLocation
                        }
                    }
                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.myLooper()
                    )
                }
            }
        } else {
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
    // endregion


    private fun onClickNext() {
        binding?.apply {
            if (selectDateTv.text != "Select Date" && selectTimeTv.text != "Select Time") {
                val listOfData = arrayOf(
                    selectDateTv.text.toString(),
                    selectTimeTv.text.toString(),
                    optionEt.text.toString(),
                    latitude.toString(),
                    longitude.toString(),
                    userLocation
                )
                Log.e(Constant.MY_TAG, listOfData.joinToString())
                val action =
                    DateRequestFragmentDirections.actionDateRequestFragmentToNotesRequestFragment(
                        args.idPets,
                        args.requestName,
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


    private fun getCityAndCountry(): String {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        var city = ""
        var country = ""

        try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0]
                city = address.locality ?: address.subLocality ?: ""
                country = address.countryName ?: ""

                // Check other address components as a fallback
                if (city.isEmpty()) {
                    for (i in 0..address.maxAddressLineIndex) {
                        val line = address.getAddressLine(i)
                        if (line.contains("city", true)) {
                            city = line
                            break
                        }
                    }
                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "$city , $country"
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