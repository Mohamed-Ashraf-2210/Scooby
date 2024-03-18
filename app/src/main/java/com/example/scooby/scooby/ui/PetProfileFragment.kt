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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.scooby.MainActivity
import com.example.scooby.R
import com.example.scooby.databinding.FragmentPetProfileBinding
import com.example.scooby.databinding.FragmentProfileBinding
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale


class PetProfileFragment : Fragment() {
    private lateinit var binding: FragmentPetProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private val args : PetProfileFragmentArgs by navArgs()

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
    private fun init() {
        profileViewModel.apply {
            getUser("65db22b7f93993b1a0b35bb3")
            profileResult.observe(viewLifecycleOwner) {
                getPetProfileData(it)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun getPetProfileData(it: ProfileDetailsResponse?) {
        val data = it?.data?.data?.pets?.get(args.petPosition)
        Glide.with(this).load(data?.petImage).into(binding.imagePetProfile)
        binding.namePet.text = data?.name
        binding.adjPet.text = data?.type
        binding.bioPet.text = data?.profileBio
        binding.genderValueTv.text = data?.gender
        binding.sizeValueTv.text = data?.size
        binding.weightValueTv.text = "${data?.weight.toString()} kg"

        val inputDateFormat = SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH)
        val outputDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

        val dateBirthDay: Date = inputDateFormat.parse(data!!.birthday) ?: Date()
        binding.birthdayTv.text = outputDateFormat.format(dateBirthDay)
        val dateAdoptionDay: Date = inputDateFormat.parse(data.adoptionDay) ?: Date()
        binding.adoptionDayTv.text = outputDateFormat.format(dateAdoptionDay)
        val yearsOld = LocalDate.now().year - dateBirthDay.year
        binding.oldYear.text = yearsOld.toString()
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).apply {
            supportActionBar?.hide()

        }
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