package com.example.scooby.scooby.services.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.ServicesProfileResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentServicesProfileBinding
import com.example.scooby.scooby.services.adapter.ReviewAdapter
import com.example.scooby.scooby.services.viewmodel.ServicesViewModel

class ServicesProfileFragment : Fragment() {
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private lateinit var rvReviews: RecyclerView
    private val args : ServicesProfileFragmentArgs by navArgs()
    private lateinit var binding: FragmentServicesProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentServicesProfileBinding.inflate(layoutInflater,container,false)
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        servicesViewModel.getServicesProfile(args.ServiceId)
        servicesViewModel.servicesProfileResult.observe(viewLifecycleOwner){
            it?.let { setDataToViews(it) }
            getReviewsData(it?.updatedDoc?.reviewsOfService)
            Log.d("Doctor_Ser",it.toString())
        }
    }

    private fun setDataToViews(servicesDetails : ServicesProfileResponse){
        binding.apply {
            imageSlider(servicesDetails)
            tvServiceName.text = servicesDetails.updatedDoc?.name ?: getString(R.string.error_loading)
            servicesPrice.text = servicesDetails.updatedDoc?.price.toString()
            servicesTime.text = servicesDetails.updatedDoc?.pricePer
            rate.rating = servicesDetails.updatedDoc?.rate?.toFloat()!!
            rateNumber.text = servicesDetails.updatedDoc?.numberOfRate.toString()
            tvAboutDescription.text = servicesDetails.updatedDoc?.about
            servicesName.text = servicesDetails.updatedDoc?.name
            servicesPriceBtn.text = servicesDetails.updatedDoc?.price.toString()
            servicesTimeBtn.text = servicesDetails.updatedDoc?.pricePer
            servicesDetails.updatedDoc?.question1?.get(0).let {
                tvQuestion1.text = it
            }
            servicesDetails.updatedDoc?.question1?.get(1).let {
                tvAnswar1.text = it
            }
            servicesDetails.updatedDoc?.question2?.get(0).let {
                tvQuestion2.text = it
            }
            servicesDetails.updatedDoc?.question2?.get(1).let {
                tvAnswar2.text = it
            }
            servicesDetails.updatedDoc?.question3?.get(0).let {
                tvQuestion3.text = it
            }
            servicesDetails.updatedDoc?.question3?.get(1).let {
                tvAnswar3.text = it
            }
        }

    }
    private fun imageSlider(servicesDetails : ServicesProfileResponse){
        val imgList = ArrayList<SlideModel>()
        val size = servicesDetails.updatedDoc?.imagesProfile?.size
        for (i in 0 until size!!){
            imgList.add(SlideModel(servicesDetails.updatedDoc!!.imagesProfile?.get(i)))
        }
        binding.servicesProfileImage.setImageList(imgList,ScaleTypes.CENTER_CROP)
        binding.servicesProfileImage.setSlideAnimation(AnimationTypes.ZOOM_OUT)
        binding.servicesProfileImage.stopSliding()
    }


    private fun getReviewsData(reviewData: List<ServicesProfileResponse.Data.ReviewsOfService?>?){
        rvReviews = binding.rvReview
        rvReviews.adapter = ReviewAdapter(reviewData!!)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            rvReviews.adapter = null
        }
    }

}