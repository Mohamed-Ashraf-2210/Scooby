package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.scooby.MainActivity
import com.example.scooby.databinding.FragmentPetProfileBinding
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import com.example.scooby.utils.Constant
import com.example.scooby.utils.TokenManager
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class PetProfileFragment : Fragment() {
    private lateinit var binding: FragmentPetProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val args : PetProfileFragmentArgs by navArgs()
    private lateinit var userId: String


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetProfileBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                init()
                isRefreshing = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                getPetProfileData(it)
            }
        }

        backOffFragment()
    }

    private fun backOffFragment() {
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun getPetProfileData(it: ProfileDetailsResponse?) {
        val pet = it?.data?.data?.pets?.get(args.petPosition)
        if (pet != null) {
            Glide.with(this).load(pet.petImage).into(binding.imagePetProfile)
            binding.namePet.text = pet.name
            binding.adjPet.text = pet.type
            binding.bioPet.text = pet.profileBio
            binding.genderValueTv.text = pet.gender
            binding.sizeValueTv.text = pet.size
            binding.weightValueTv.text = "${pet.weight} kg"

            val inputDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH)
            val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

            val dateBirthDay: Date? = inputDateFormat.parse(pet.birthday)
            binding.birthdayTv.text = dateBirthDay?.let { outputDateFormat.format(it) } ?: "Unknown"
            val dateAdoptionDay: Date? = inputDateFormat.parse(pet.adoptionDay)
            binding.adoptionDayTv.text = dateAdoptionDay?.let { outputDateFormat.format(it) } ?: "Unknown"
            // Calculate age
            val yearsOld = dateBirthDay?.let { calculateAge(it) }
            binding.oldYear.text = "${yearsOld?.toString() ?: "Unknown"} y.o"
        } else {
            // Handle the case where data is null
            // For example, show a message or hide views
        }
    }

    private fun calculateAge(birthDate: Date): Int {
        val birthCalendar = Calendar.getInstance()
        birthCalendar.time = birthDate
        val currentDate = Calendar.getInstance()

        var age = currentDate.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).hideBottomNavigationView()
    }
    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}