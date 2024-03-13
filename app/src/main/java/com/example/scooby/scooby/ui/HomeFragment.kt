package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.scooby.R
import com.example.scooby.databinding.FragmentHomeBinding
import com.example.scooby.scooby.adapter.BlogHomeAdapter
import com.example.scooby.scooby.adapter.PetsHomeAdapter
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.data.model.AllPetsResponse
import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.scooby.data.model.OfferResponse
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.viewmodel.BlogViewModel
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.PetsViewModel
import com.example.scooby.scooby.viewmodel.ServicesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private val blogsViewModel by viewModels<BlogViewModel>()
    private val petsViewModel by viewModels<PetsViewModel>()
    private val offerViewModel by viewModels<OfferViewModel>()
    private lateinit var mContext: Context
    private lateinit var servicesRV: RecyclerView
    private lateinit var blogsRV: RecyclerView
    private lateinit var petsRV: RecyclerView

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mContext = requireContext()
        init()


        binding.vetIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_vetFragment)
        }
        binding.boardingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_boardingFragment)
        }
        binding.sittingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sittingFragment)
        }
        binding.suppliesIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_suppliesFragment)
        }
        binding.petFriendlyPlacesIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_petFriendlyPlacesFragment)
        }
        binding.groomingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_groomingFragment)
        }
        binding.trainingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_trainingFragment)
        }
        binding.blogsSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_blogsFragment)
        }
        binding.servicesSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_servicesFragment)
        }

        binding.moreIcon.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.menu_bottom_sheet, null)
            val dialog = BottomSheetDialog(
                requireContext(),
                com.google.android.material.R.style.Theme_Material3_Light_BottomSheetDialog
            )
            dialog.setContentView(dialogView)
            dialog.show()
        }

        return binding.root
    }


    private fun init() {
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner) {
                getServicesData(it)
            }
        }

        petsViewModel.apply {
            getPets()
            petsResult.observe(viewLifecycleOwner) {
                getPetsData(it)
            }
        }

        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                getBlogsData(it)
            }
        }

        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) {
                getOfferData(it)
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
        binding.imageSlider.setImageList(imgList, ScaleTypes.CENTER_CROP)
        binding.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)
    }

    private fun getServicesData(data: ServicesResponse?) {
        servicesRV = binding.servicesRv
        servicesRV.adapter = ServicesAdapter(data!!, requireContext())
    }

    private fun getPetsData(data: AllPetsResponse?) {
        petsRV = binding.petsRv
        petsRV.adapter = PetsHomeAdapter(data!!, requireContext())
    }

    private fun getBlogsData(data: BlogResponse?) {
        blogsRV = binding.blogsRv
        blogsRV.adapter = BlogHomeAdapter(data!!, requireContext())
    }
    // endregion
}