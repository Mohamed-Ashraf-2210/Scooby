package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenCreated
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Constant
import com.example.domain.paws.adaption.AdaptionAdoptMeResponse
import com.example.domain.paws.adaption.AdaptionCatsResponse
import com.example.domain.paws.adaption.AdaptionDogsResponse
import com.example.domain.paws.adaption.AdaptionResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import com.example.data.local.TokenManager
import com.example.scooby.R
import com.example.scooby.databinding.FragmentPawsBinding
import com.example.scooby.scooby.paws.adapter.AdaptionAdoptMeAdapter
import com.example.scooby.scooby.paws.adapter.AdaptionCatsAdapter
import com.example.scooby.scooby.paws.adapter.AdaptionDogsAdapter
import com.example.scooby.scooby.paws.adapter.CatsMissingAdapter
import com.example.scooby.scooby.paws.adapter.DogsMissingAdapter
import com.example.scooby.scooby.paws.adapter.GetRecentlyAdapter
import com.example.scooby.scooby.paws.adapter.MarginBetweenDecoration
import com.example.scooby.scooby.paws.adapter.PawsTopColAdapter
import com.example.scooby.scooby.paws.adapter.PetsShelterAdapter
import com.example.scooby.scooby.paws.adapter.ShelterAdapter
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.scooby.services.viewmodel.ServicesViewModel
import com.example.scooby.utils.BaseResponse

class PawsFragment : Fragment() {
    private lateinit var pawsViewModel : PawsViewModel
    private lateinit var binding : FragmentPawsBinding
    private lateinit var currentUserId: String
    private lateinit var rvTopCol: RecyclerView
    private lateinit var rvCats: RecyclerView
    private lateinit var rvDogs: RecyclerView
    private lateinit var rvAdoptMe: RecyclerView
    private lateinit var rvShelters: RecyclerView
    private lateinit var rvPetsShelter: RecyclerView
    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_open_anim) }
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.rotet_close_anim) }
    private val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom : Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private var clicked = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPawsBinding.inflate(inflater, container, false)
        pawsViewModel = ViewModelProvider(this)[PawsViewModel::class.java]
        currentUserId = TokenManager.getAuth(Constant.USER_ID).toString()
        initButtonCallBack()
        init()
        return binding.root
    }

    private fun init() {
        observeOfferViewModel()
        val recyclerView = binding.rvPetsInShelter
        val spaceDecoration = MarginBetweenDecoration(16)
        recyclerView.addItemDecoration(spaceDecoration)
    }

    private fun observeOfferViewModel() {
        //For Top Collection of dogs and cat Adapter in Adaption Fragment
        pawsViewModel.apply {
            getTopCollection()
            topCollectionRes.observe(viewLifecycleOwner) {
                stopLoading()
                getDataTopCollection(it)
                Log.d("TOP_COL_RES", it.data.toString())
            }
        }
        //For Cats Adapter in Adaption Fragment
        pawsViewModel.apply {
            getCats()
            catsResult.observe(viewLifecycleOwner) {
                stopLoading()
                getCatsData(it)
                Log.d("CATS_RES", it.data.toString())
            }
        }

        //For Dogs Adapter in Adaption Fragment
        pawsViewModel.apply {
            getDogs()
            dogResult.observe(viewLifecycleOwner) {
                stopLoading()
                getDogsData(it)
                Log.d("Dogs_RES", it.data.toString())
            }
        }

        //For Adopt me Adapter in Adaption Fragment
        pawsViewModel.apply {
            getAdoptMe()
            adoptMeResult.observe(viewLifecycleOwner) {
                stopLoading()
                getAdoptMeData(it)
                Log.d("AdoptMe_RES", it.data.toString())
            }
        }


        pawsViewModel.apply {
            getShelters()
            shelterResult.observe(viewLifecycleOwner) {
                stopLoading()
                getShelterData(it)
                Log.d("SHELTER_RES", it.allShelters.toString())
            }
        }

        pawsViewModel.apply {
            getPetsShelters()
            petsShelterResult.observe(viewLifecycleOwner) { petsData ->
                stopLoading()
                getPetsShelterData(petsData)
            }
        }

        setUpObservableMissingSection()


    }

    private fun setUpObservableMissingSection() {
        pawsViewModel.apply {
            getCatsMissing()
            catsMissingResult.observe(viewLifecycleOwner){catResponse->
                when(catResponse){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        binding.rvMissingCat.apply {
                            adapter = catResponse.data?.let { CatsMissingAdapter(it) }
                        }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast()
                    }
                }
            }

            getDogsMissing()
            dogsMissingResult.observe(viewLifecycleOwner){dogResponse->
                when(dogResponse){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        binding.rvMissingDog.apply {
                            adapter = dogResponse.data?.let { DogsMissingAdapter(it) }
                        }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast()
                    }
                }
            }

            getRecentlyMissing()
            recentlyMissingResult.observe(viewLifecycleOwner){recentlyResponse->
                when(recentlyResponse){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        binding.rvAddedMissPet.apply {
                            adapter = recentlyResponse.data?.let { GetRecentlyAdapter(it) }
                        }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast()
                    }
                }
            }
        }
    }

    private fun initButtonCallBack() {
        binding.btnAdaption.setOnClickListener {
            binding.adaptionFragment.visibility = View.VISIBLE
            binding.rescueFragment.visibility = View.GONE
            binding.missingFragment.visibility = View.GONE
        }
        binding.btnRescue.setOnClickListener {
            binding.rescueFragment.visibility = View.VISIBLE
            binding.adaptionFragment.visibility = View.GONE
            binding.missingFragment.visibility = View.GONE

        }
        binding.btnMissing.setOnClickListener {
            binding.missingFragment.visibility = View.VISIBLE
            binding.rescueFragment.visibility = View.GONE
            binding.adaptionFragment.visibility = View.GONE
        }
        //fab animation

        binding.floatingActionButton.setOnClickListener {
            onAddButtonClicked()
        }
        binding.editButton.setOnClickListener {
            Toast.makeText(requireContext(),"clicked edit Button",Toast.LENGTH_SHORT).show()
        }
        binding.imageButton.setOnClickListener {
            Toast.makeText(requireContext(),"clicked image Button",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }
    private fun setVisibility(clicked:Boolean) {
        if(!clicked){
            binding.editButton.visibility= View.VISIBLE
            binding.imageButton.visibility = View.VISIBLE
        }else{
            binding.editButton.visibility= View.INVISIBLE
            binding.imageButton.visibility = View.INVISIBLE
        }
    }
    private fun setAnimation(clicked:Boolean) {
        if(!clicked){
            binding.editButton.startAnimation(fromBottom)
            binding.imageButton.startAnimation(fromBottom)
            binding.floatingActionButton.startAnimation(rotateOpen)
        }else{
            binding.editButton.startAnimation(toBottom)
            binding.imageButton.startAnimation(toBottom)
            binding.floatingActionButton.startAnimation(rotateClose)
        }
    }



    private fun getDataTopCollection(data: AdaptionResponse) {
        rvTopCol = binding.rvTopCol
        rvTopCol.adapter = PawsTopColAdapter(data,pawsViewModel,currentUserId)
    }

    private fun getCatsData(data: AdaptionCatsResponse) {
        rvCats = binding.rvCats
        rvCats.adapter = AdaptionCatsAdapter(data)
    }

    private fun getDogsData(data: AdaptionDogsResponse) {
        rvDogs = binding.rvDogs
        rvDogs.adapter = AdaptionDogsAdapter(data)
    }

    private fun getAdoptMeData(data: AdaptionAdoptMeResponse) {
        rvAdoptMe = binding.rvAdoptMe
        rvAdoptMe.adapter = AdaptionAdoptMeAdapter(data,currentUserId,pawsViewModel)
    }
    private fun getShelterData(data: ShelterResponse) {
        rvShelters = binding.rvShelters
        rvShelters.adapter = ShelterAdapter(data)
    }

    private fun getPetsShelterData(data: PetsInShelterResponse) {
        rvPetsShelter = binding.rvPetsInShelter
        rvPetsShelter.adapter = PetsShelterAdapter(data)
    }
    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.apply {
            loading.visibility = View.GONE
        }
    }
    private fun showToast() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
    }

}