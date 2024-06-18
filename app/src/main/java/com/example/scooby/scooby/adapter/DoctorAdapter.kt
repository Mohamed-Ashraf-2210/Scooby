package com.example.scooby.scooby.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.doctors.DoctorsResponse
import com.example.scooby.databinding.DoctorsItemBinding
import com.example.scooby.scooby.doctor.DoctorsFragmentDirections

class DoctorAdapter(private val doctorList: DoctorsResponse, private val context: Context) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(private val itemDoctorsItemBinding: DoctorsItemBinding) :
        RecyclerView.ViewHolder(itemDoctorsItemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(doctorsResponse: DoctorsResponse.Doctor) {
            doctorsResponse.doctorImage.let {
                Glide.with(context)
                    .load(it!!)
                    .transform(CenterCrop(), RoundedCorners(24))
                    .into(itemDoctorsItemBinding.imageDoctorVet)
            }
            itemDoctorsItemBinding.nameDoctorTv.text = doctorsResponse.name
            itemDoctorsItemBinding.descriptionDoctorTv.text = doctorsResponse.description
            itemDoctorsItemBinding.reviewDoctorTv.text = doctorsResponse.review
            itemDoctorsItemBinding.numberOfRateDoctorTv.text =
                "(${doctorsResponse.numberOfRate})"
            itemDoctorsItemBinding.rateBarVeterinary.rating = doctorsResponse.rate?.toFloat()!!
            itemDoctorsItemBinding.rateDoctorTv.text = doctorsResponse.rate.toString()
            navigate2ShelterProfile(doctorsResponse)
        }

        private fun navigate2ShelterProfile(doctorsResponse: DoctorsResponse.Doctor) {
            itemDoctorsItemBinding.btnBookDoctor.setOnClickListener {
                val action = doctorsResponse.id.let { doctorInfo ->
                    DoctorsFragmentDirections.actionDoctorsFragmentToDoctorProfileFragment(
                        doctorInfo!!
                    )
                }
                it.findNavController().navigate(action)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(
            DoctorsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = doctorList.doctors.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        doctorList.doctors[position].let { holder.bind(it) }
    }
}