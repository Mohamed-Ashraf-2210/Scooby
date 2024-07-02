package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.pet.MyPetsResponse
import com.example.scooby.databinding.FragmentPetProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewModels.PetsViewModel
import com.example.scooby.utils.BaseResponse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class PetProfileFragment : Fragment() {
    private var binding: FragmentPetProfileBinding? = null
    private lateinit var petViewModel: PetsViewModel
    private val args: PetProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetProfileBinding.inflate(inflater, container, false)
        petViewModel = ViewModelProvider(this)[PetsViewModel::class.java]
        init()
        observeProfileData()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            swipeRefreshLayout.setOnRefreshListener {
                observeProfileData()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun init() {
        binding?.apply {
            backProfile.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeProfileData() {
        petViewModel.apply {
            getMyPets()
            myPetsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        if (it.data != null) {
                            getPetProfileData(it.data)
                        } else {
                            showToast("Null Data")
                        }
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun getPetProfileData(myPetsResponse: MyPetsResponse?) {
        val pet = myPetsResponse?.data?.get(args.petPosition)!!
        binding?.apply {
            Glide.with(this@PetProfileFragment).load(pet.petImage).into(imagePetProfile)
            namePet.text = pet.name
            adjPet.text = pet.type
            bioPet.text = pet.profileBio
            genderValueTv.text = pet.gender
            sizeValueTv.text = pet.size
            "${pet.weight} kg".also { weightValueTv.text = it }
            formatDate(birthdayTv, pet.birthday)
            formatDate(adoptionDayTv, pet.adoptionDay)
            calculateAndSetAge(oldYear, pet.birthday)
        }
    }

    private fun formatDate(view: TextView, dateString: String) {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        inputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date? = inputDateFormat.parse(dateString)
        view.text = date?.let { outputDateFormat.format(it) } ?: "Unknown"
    }


    private fun calculateAndSetAge(view: TextView, birthDate: String) {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        inputDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date? = inputDateFormat.parse(birthDate)
        val age = date?.let { calculateAge(it) } ?: "Unknown"
        "$age y.o".also { view.text = it }
    }

    private fun calculateAge(birthDate: Date): Int {
        val today = Calendar.getInstance()
        val birthDateCalendar = Calendar.getInstance()
        birthDateCalendar.time = birthDate

        var age = today.get(Calendar.YEAR) - birthDateCalendar.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < birthDateCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        return age
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
