package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.blog.BlogResponse
import com.example.domain.offer.OfferResponse
import com.example.domain.pet.AllPetsResponse
import com.example.domain.services.ServicesResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentHomeBinding
import com.example.scooby.scooby.adapter.BlogHomeAdapter
import com.example.scooby.scooby.adapter.PetsHomeAdapter
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.viewmodel.BlogViewModel
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.PetsViewModel
import com.example.scooby.scooby.viewmodel.ServicesViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private val blogsViewModel by viewModels<BlogViewModel>()
    private val petsViewModel by viewModels<PetsViewModel>()
    private val offerViewModel by viewModels<OfferViewModel>()


    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding?.root
    }

    private fun init() {
        observeViewModel()
        requestsSection()
        seeMore()
    }

    private fun observeViewModel() {
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    stopLoading()
                    getServicesData(it)
                }
            }
        }

        petsViewModel.apply {
            getPets()
            petsResult.observe(viewLifecycleOwner) {
                if (it != null)
                    getPetsData(it)
            }
        }

        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                if (it != null)
                    getBlogsData(it)
            }
        }

        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) {
                if (it != null)
                    getOfferData(it)
            }
        }
    }

    private fun seeMore() {
        binding?.apply {
            blogsSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_blogsFragment)
            }
            servicesSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_servicesFragment)
            }
            petFriendlyPlacesIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_vetFragment)
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun requestsSection() {
        binding?.apply {
            vetIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_vetFragment)
            }
            moreIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_menuBottomSheetFragment)
            }
        }
    }


    // region Get Data
    private fun getOfferData(data: OfferResponse?) {
        val imgList = ArrayList<SlideModel>()
        val sizeOfList = data?.data?.size
        for (i in 0..<sizeOfList!!) {
            imgList.add(SlideModel(data.data[i].offerImage))
        }
        binding?.imageSlider?.setImageList(imgList, ScaleTypes.FIT)
        binding?.imageSlider?.setSlideAnimation(AnimationTypes.ZOOM_OUT)
    }

    private fun getServicesData(data: ServicesResponse?) {
        binding?.servicesRv?.adapter = ServicesAdapter(data!!, requireContext())
    }

    private fun getPetsData(data: AllPetsResponse?) {
        binding?.petsRv?.adapter = PetsHomeAdapter(data!!, requireContext())
    }

    private fun getBlogsData(data: BlogResponse?) {
        binding?.blogsRv?.adapter = BlogHomeAdapter(data!!, requireContext())
    }
    // endregion

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            homeContent.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.apply {
            servicesRv.adapter = null
            petsRv.adapter = null
            petsRv.adapter = null
        }
    }
}
