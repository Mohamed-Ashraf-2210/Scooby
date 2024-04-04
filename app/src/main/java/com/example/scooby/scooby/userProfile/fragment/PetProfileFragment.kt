package com.example.scooby.scooby.userProfile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.data.Constant
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentPetProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.userProfile.viewModel.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PetProfileFragment : Fragment() {
    private lateinit var binding: FragmentPetProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val args: PetProfileFragmentArgs by navArgs()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetProfileBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            init()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun init() {
        observeProfileData()
        backOffFragment()
    }

    private fun observeProfileData() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        profileViewModel.getUser(userId)
        profileViewModel.profileResult.observe(viewLifecycleOwner) { result ->
            stopLoading()
            getPetProfileData(result)
        }
    }

    private fun getPetProfileData(userProfileResponse: UserProfileResponse?) {
        val pet = userProfileResponse?.data?.data?.pets?.get(args.petPosition)
        pet?.let {
            with(binding) {
                Glide.with(this@PetProfileFragment).load(it.petImage).into(imagePetProfile)
                namePet.text = it.name
                adjPet.text = it.type
                bioPet.text = it.profileBio
                genderValueTv.text = it.gender
                sizeValueTv.text = it.size
                weightValueTv.text = "${it.weight} kg"
                formatDate(binding.birthdayTv, it.birthday)
                formatDate(binding.adoptionDayTv, it.adoptionDay)
                calculateAndSetAge(binding.oldYear, it.birthday)
            }
        } ?: showError()
    }

    private fun formatDate(view: TextView, dateString: String) {
        val inputDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH)
        val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        val date: Date? = inputDateFormat.parse(dateString)
        view.text = date?.let { outputDateFormat.format(it) } ?: "Unknown"
    }

    private fun calculateAndSetAge(view: TextView, birthDate: String) {
        val date: Date? = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH)
            .parse(birthDate)
        val age = date?.let { calculateAge(it) } ?: "Unknown"
        view.text = "$age y.o"
    }

    private fun calculateAge(birthDate: Date): Int {
        val birthCalendar = Calendar.getInstance().apply { time = birthDate }
        val currentDate = Calendar.getInstance()

        var age = currentDate.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)
        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }

    private fun stopLoading() {
        with(binding) {
            loading.visibility = View.GONE
            petProfileContent.visibility = View.VISIBLE
        }
    }

    private fun backOffFragment() {
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showError() {
        // Handle the case where data is null
        // For example, show a message or hide views
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
