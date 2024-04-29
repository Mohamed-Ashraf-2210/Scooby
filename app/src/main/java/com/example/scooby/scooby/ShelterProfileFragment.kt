package com.example.scooby.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.PetShelterProfileResponse
import com.example.domain.ShelterProfileResponse
import com.example.scooby.databinding.FragmentShelterProfileBinding


class ShelterProfileFragment : Fragment() {
    private lateinit var rvReviews: RecyclerView
    private lateinit var rvPets: RecyclerView
    private lateinit var binding : FragmentShelterProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShelterProfileBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        observeViewModel()
    }

    private fun observeViewModel() {

    }

    private fun setDataToViews(shelterProfileResponse:ShelterProfileResponse) {
        binding.apply {
            imageSlider(shelterProfileResponse)
            tvShelterName.text = shelterProfileResponse.shelterName
            rate.rating = shelterProfileResponse.rate!!.toFloat()
            rateNumber.text = shelterProfileResponse.numberOfRates.toString()
            tvLocation.text = shelterProfileResponse.locations?.address
            tvDurationTime.text = shelterProfileResponse.description
            tvAboutDescription.text = shelterProfileResponse.about
        }
    }
    private fun imageSlider(shelterProfileResponse:ShelterProfileResponse){
        val imgList = ArrayList<SlideModel>()
        val size = shelterProfileResponse.shelterImages?.size
        for (i in 0 until size!!){
            imgList.add(SlideModel(shelterProfileResponse.shelterImages!![i]))
        }
        binding.shelterProfileImage.setImageList(imgList, ScaleTypes.CENTER_CROP)
        binding.shelterProfileImage.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        binding.shelterProfileImage.stopSliding()
    }

    private fun getReviewsData(reviewData: List<ShelterProfileResponse.ReviewsOfShelter>){
        rvReviews = binding.rvReview
        rvReviews.adapter = ReviewShelterAdapter(reviewData)
    }
    fun getPetData(petsData : List<PetShelterProfileResponse.PetShelterProfileResponseItem>){
        rvPets = binding.rvPets
        rvPets.adapter = PetInShelterProfileAdapter(petsData)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            rvReviews.adapter = null
            rvPets.adapter = null
        }
    }
}