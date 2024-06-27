package com.example.scooby.scooby.userProfile

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
import com.example.data.utils.Constant
import com.example.domain.pet.MyPetsResponse
import com.example.scooby.R
import com.example.data.local.TokenManager
import com.example.scooby.databinding.FragmentPetProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewModels.PetsViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PetProfileFragment : Fragment() {
    private var binding: FragmentPetProfileBinding? = null
    private val profileViewModel by viewModels<PetsViewModel>()
    private val args: PetProfileFragmentArgs by navArgs()
    private lateinit var userId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetProfileBinding.inflate(inflater, container, false)
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
            editProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
        }
    }

    private fun observeProfileData() {
        userId = TokenManager.getAuth( Constant.USER_ID).toString()
        profileViewModel.getMyPets()
        profileViewModel.myPetsResult.observe(viewLifecycleOwner) { result ->
            stopLoading()
            //getPetProfileData(result)
        }
    }

    private fun getPetProfileData(myPetsResponse: MyPetsResponse?) {
        val pet = myPetsResponse?.data?.get(args.petPosition)
        pet?.let {
            binding?.apply {
                Glide.with(this@PetProfileFragment).load(it.petImage).into(imagePetProfile)
                namePet.text = it.name
                adjPet.text = it.type
                bioPet.text = it.profileBio
                genderValueTv.text = it.gender
                sizeValueTv.text = it.size
                "${it.weight} kg".also { weightValueTv.text = it }
                formatDate(birthdayTv, it.birthday)
                formatDate(adoptionDayTv, it.adoptionDay)
                calculateAndSetAge(oldYear, it.birthday)
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
        "$age y.o".also { view.text = it }
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
        binding?.apply {
            loading.visibility = View.GONE
            petProfileContent.visibility = View.VISIBLE
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
