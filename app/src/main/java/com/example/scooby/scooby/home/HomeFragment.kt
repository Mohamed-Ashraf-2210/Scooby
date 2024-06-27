package com.example.scooby.scooby.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.domain.blog.BlogResponse
import com.example.domain.offer.OfferResponse
import com.example.domain.pet.PetsResponse
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentHomeBinding
import com.example.scooby.scooby.adapter.BlogHomeAdapter
import com.example.scooby.scooby.adapter.PetsHomeAdapter
import com.example.scooby.scooby.services.adapter.ServicesAdapter
import com.example.scooby.scooby.services.viewmodel.ServicesViewModel
import com.example.scooby.scooby.viewModels.BlogViewModel
import com.example.scooby.scooby.viewModels.OfferViewModel
import com.example.scooby.scooby.viewModels.PetsViewModel
import com.example.scooby.scooby.viewModels.ProfileViewModel
import com.example.scooby.utils.BaseResponse

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var offerViewModel: OfferViewModel
    private lateinit var servicesViewModel: ServicesViewModel
    private lateinit var petsViewModel: PetsViewModel
    private lateinit var blogsViewModel: BlogViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentHomeBinding.inflate(inflater, container, false)
            profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            servicesViewModel = ViewModelProvider(this)[ServicesViewModel::class.java]
            offerViewModel = ViewModelProvider(this)[OfferViewModel::class.java]
            petsViewModel = ViewModelProvider(this)[PetsViewModel::class.java]
            blogsViewModel = ViewModelProvider(this)[BlogViewModel::class.java]
            initView()
        }
        return binding?.root
    }

    private fun initView() {
        observeViewModel()
        clickActions()
    }

    private fun clickActions() {
        binding?.apply {
            userImage.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            blogsSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_blogsFragment)
            }
            servicesSeeMore.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_servicesFragment)
            }
            vetIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_vetFragment)
            }
            boardingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Boarding",
                    "20"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            sittingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Sitting",
                    "30"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            petFriendlyPlacesIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Pet Friendly Places",
                    "25"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            groomingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Grooming",
                    "35"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            trainingIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Training",
                    "40"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            suppliesIcon.setOnClickListener {
                val requestName = arrayOf(
                    "Supplies",
                    "45"
                )
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSelectPetFragment(requestName)
                findNavController().navigate(action)
            }
            moreIcon.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_menuBottomSheetFragment)
            }
        }
    }


    private fun observeViewModel() {
        observeUserProfile()
        observeOffers()
        observeServices()
        observePets()
        observeBlogs()
    }

    // region User Profile
    private fun observeUserProfile() {
        profileViewModel.apply {
            getUserInfo()
            profileResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        profileSuccess(it.data)
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun profileSuccess(data: UserProfileResponse?) {
        val userInfo = data?.data?.data
        binding?.apply {
            val name = userInfo?.name
            if (name != null) {
                for (i in name.indices) {
                    // Take the first name of the user
                    if (name[i] == ' ') {
                        userName.text = "Hi, ${name.substring(0, i)}"
                        break
                    }
                }
            }
            Glide.with(requireContext())
                .load(userInfo?.profileImage)
                .placeholder(R.drawable.user_default_image)
                .error(R.drawable.error)
                .into(userImage)
        }
    }
    // endregion

    // region Offers
    private fun observeOffers() {
        offerViewModel.apply {
            getOffer()
            offerResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        getOffersData(it.data)
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun getOffersData(data: OfferResponse?) {
        val imgList = ArrayList<SlideModel>()
        val sizeOfList = data?.data?.size
        for (i in 0..<sizeOfList!!) {
            imgList.add(SlideModel(data.data[i].offerImage))
        }
        binding?.imageSlider?.setImageList(imgList, ScaleTypes.FIT)
        binding?.imageSlider?.setSlideAnimation(AnimationTypes.ZOOM_OUT)
    }
    // endregion

    // region Services
    private fun observeServices() {
        servicesViewModel.apply {
            getServices()
            servicesResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        binding?.servicesRv?.adapter = ServicesAdapter(it.data!!)
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    // endregion

    // region Pets
    private fun observePets() {
        petsViewModel.apply {
            getPets()
            petsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        getPetsData(it.data)
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun getPetsData(data: PetsResponse?) {
        binding?.petsRv?.adapter = PetsHomeAdapter(data!!, requireContext())
    }
    // endregion

    // region Blogs
    private fun observeBlogs() {
        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        getBlogsData(it.data)
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun getBlogsData(data: BlogResponse?) {
        binding?.blogsRv?.adapter = BlogHomeAdapter(data!!)
    }
    // endregion

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            homeContent.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
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
