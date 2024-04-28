package com.example.scooby.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.ServicesProfileResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentServicesProfileBinding
import com.example.scooby.utils.loadUrl

class ServicesProfileFragment : Fragment() {
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

        return binding.root
    }

    fun setDataToViews(servicesDetails : ServicesProfileResponse){
        binding.apply {
            servicesDetails.updatedDoc?.imagesProfile?.get(0)
                ?.let { servicesProfileImage.loadUrl(it) }
            servicesName.text = servicesDetails.updatedDoc?.name ?: getString(R.string.error_loading)
            servicesPrice.text = servicesDetails.updatedDoc?.price.toString()
            servicesTime.text = servicesDetails.updatedDoc?.pricePer
            rate.rating = servicesDetails.updatedDoc?.rate?.toFloat()!!
            rateNumber.text = servicesDetails.updatedDoc?.numberOfRate.toString()
            tvAboutDescription.text = servicesDetails.updatedDoc?.description
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

}