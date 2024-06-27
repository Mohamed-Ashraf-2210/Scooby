package com.example.scooby.scooby.doctor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.DoctorProfileResponse
import com.example.scooby.databinding.FragmentDoctorProfileBinding
import com.example.scooby.scooby.adapter.ReviewDoctorAdapter
import com.example.scooby.scooby.viewModels.VetViewModel

class DoctorProfileFragment : Fragment() {
    private val vetViewModel by viewModels<VetViewModel>()
    private lateinit var rvReview : RecyclerView
    private lateinit var binding : FragmentDoctorProfileBinding
    private val args : DoctorProfileFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDoctorProfileBinding.inflate(inflater, container, false)
        observeViewModel()
        return binding.root
    }
    private fun observeViewModel(){
        vetViewModel.getDoctorProfile(args.doctorId)
        vetViewModel.doctorProfileResult.observe(viewLifecycleOwner){
            if (it != null) {
                getReviewData(it.updatedDoc?.reviewsOfDoctor)
            }
            if (it != null) {
                setDataToViews(it)
            }
            Log.d("Profile_Ser",it.toString())
        }
    }
    private fun setDataToViews(doctorResponse: DoctorProfileResponse){
        binding.apply {
            imageSlider(doctorResponse)
            tvDoctorName.text = doctorResponse.updatedDoc?.name
            tvLocation.text = doctorResponse.updatedDoc?.description
            tvAboutDescription.text = doctorResponse.updatedDoc?.about
            doctorResponse.updatedDoc?.specializedIn?.get(0).let {
                Specialized1.text = it
            }
            doctorResponse.updatedDoc?.specializedIn?.get(1).let {
                Specialized2.text = it
            }
            doctorResponse.updatedDoc?.specializedIn?.get(2).let {
                Specialized3.text = it
            }
            doctorResponse.updatedDoc?.specializedIn?.get(3).let {
                Specialized4.text = it
            }


        }
    }
    private fun imageSlider(imageResponse: DoctorProfileResponse){
        val imgList = ArrayList<SlideModel>()
        val size = imageResponse.updatedDoc?.imagesProfile?.size
        for (i in 0 until size!!){
            imgList.add(SlideModel(imageResponse.updatedDoc!!.imagesProfile!![i]))
        }
        binding.doctorProfileImage.setImageList(imgList, ScaleTypes.CENTER_CROP)
        binding.doctorProfileImage.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        binding.doctorProfileImage.stopSliding()
    }

    private fun getReviewData(reviewData: List<DoctorProfileResponse.UpdatedDoc.ReviewsOfDoctor?>?){
        rvReview = binding.rvReview
        rvReview.adapter = ReviewDoctorAdapter(reviewData!!)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            rvReview.adapter = null
        }
    }
}