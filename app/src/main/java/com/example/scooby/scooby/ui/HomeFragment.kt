package com.example.scooby.scooby.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.scooby.R
import com.example.scooby.utils.BaseResponse
import com.example.scooby.databinding.FragmentHomeBinding
import com.example.scooby.scooby.adapter.BlogHomeAdapter
import com.example.scooby.scooby.adapter.ServicesAdapter
import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.scooby.data.model.ServicesResponse
import com.example.scooby.scooby.viewmodel.BlogViewModel
import com.example.scooby.scooby.viewmodel.ServicesViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val servicesViewModel by viewModels<ServicesViewModel>()
    private val blogsViewModel by viewModels<BlogViewModel>()
    private lateinit var mContext: Context
    private lateinit var servicesRV: RecyclerView
    private lateinit var blogsRV: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        mContext = requireContext()
        init()
        servicesViewModel.servicesResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
//                    showLoading()
                }

                is BaseResponse.Success -> {
//                    stopLoading()
                    getServicesData(it.data)
                }

                is BaseResponse.Error -> {
//                    stopLoading()
//                    processError(it.msg)
                }
                else -> {
//                    stopLoading()
                }
            }
        }
        blogsViewModel.blogResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
//                    showLoading()
                }

                is BaseResponse.Success -> {
//                    stopLoading()
                    getBlogsData(it.data)
                }

                is BaseResponse.Error -> {
//                    stopLoading()
//                    processError(it.msg)
                }
                else -> {
//                    stopLoading()
                }
            }
        }
//        binding.topAppBar.apply {
//            setNavigationOnClickListener {
//                binding.drawerLayout.open()
//            }
//            setOnMenuItemClickListener {
//                Navigation.findNavController(this).navigate(R.id.action_homeFragment_to_inboxFragment)
//                true
//            }
//        }
//        binding.navigationView.setNavigationItemSelectedListener {
//            it.isChecked = true
//            binding.drawerLayout.close()
//            true
//        }

        binding.vetIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_vetFragment)
        }
        binding.boardingIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_boardingFragment)
        }
        binding.sittingIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_sittingFragment)
        }
        binding.suppliesIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_suppliesFragment)
        }
        binding.petFriendlyPlacesIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_petFriendlyPlacesFragment)
        }
        binding.groomingIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_groomingFragment)
        }
        binding.trainingIcon.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_trainingFragment)
        }
        binding.blogsSeeMore.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_blogsFragment)
        }


        // actionBar == null
        activity?.actionBar?.apply {
            title = "Hi, Fatma"
        }
        /* supportActionBar == null */
//        (activity as AppCompatActivity).supportActionBar?.title = "Home 2"

        return binding.root
    }

    private fun getBlogsData(data: BlogResponse?) {
        blogsRV = binding.blogsRv
        blogsRV.adapter = BlogHomeAdapter(data!!,requireContext())
    }

    private fun init() {
        discount()
        servicesViewModel.getServices()
        blogsViewModel.getBlogs()
    }


    private fun getServicesData(data: ServicesResponse?) {
        servicesRV = binding.servicesRv
        servicesRV.adapter = ServicesAdapter(data!!,requireContext())
    }


    private fun discount() {
        val imgList = ArrayList<SlideModel>()
        imgList.add(SlideModel(R.id.user_image))
        imgList.add(SlideModel("https://s3-alpha-sig.figma.com/img/b942/f329/7458e2f3c8eaa5bbb87439b0b51feee3?Expires=1710720000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ZJNPYgfkpEXP5tjeVW~OO4qywsj34xbcSe4fh2YRwWPvXTg9kJ1~0iMjG2U3at1SEfUkr8gEu068h-cdL3qFCJCpnwmWOw6w47ikDunhedg783-LvBiF5E~k2QiSa5CtKvvAYamk2SenJRFoIS9mP8Lr63v9oxg~XpRCoA8F89iHcc3nJL67qWaw8cENdU6slfuoQUqphpU2yOri5aD3es6L5aLeD19wGAo7yhuK-h5VKZ23EZMBtSdU8BcT-Kc5vlxehmbw8Qppp7K3xaeL5XVDtn5En-vWLiR~Wp6az53eVf4ucA3hE8tfKJWMCjylN71~ndo215A6YAvjxGaPxA__"))
        imgList.add(SlideModel("https://s3-alpha-sig.figma.com/img/2793/085f/6103dac34bb37e214c315326a726b1a1?Expires=1710720000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=UW7gpMi~epi6nmM7WYBAYULyg7b5pZ4~WFVP0E36j1Vn5avN7LxOd074uhfjAEfB0delHibUhr1X7MxEpFxCp6uSfJsEyalDWAY8xBdtZFNEJtSKyu0nnZQm7pIKK5~gkgrqMI3EWeGv570aekqTFUywKZzQ8V9oTgGAkfKhE61uepWOXZMlP3weXwc3t99Zq8mG0So6lB0a8eayqu55JCgljWUtwLc3M2UQx8vAY~tFl57aIJ47XkQrl1If--id5WS90ck7e-IgotKAR6mkW1Ux6slfSmvEJUeArdNx-kK1ZLdajRcjQF1jYQPbp0-fSwfh5DwkZLF1n802Wm3tJw__"))
        binding.imageSlider.setImageList(imgList,ScaleTypes.FIT)
        binding.imageSlider.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)
    }
}