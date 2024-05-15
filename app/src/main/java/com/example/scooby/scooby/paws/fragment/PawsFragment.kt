package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Constant
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import com.example.domain.paws.rescue.PetsInShelterResponse
import com.example.domain.paws.rescue.ShelterResponse
import com.example.scooby.utils.TokenManager
import com.example.scooby.databinding.FragmentPawsBinding
import com.example.scooby.scooby.paws.adapter.AdaptionAdoptMeAdapter
import com.example.scooby.scooby.paws.adapter.AdaptionCatsAdapter
import com.example.scooby.scooby.paws.adapter.AdaptionDogsAdapter
import com.example.scooby.scooby.paws.adapter.MarginBetweenDecoration
import com.example.scooby.scooby.paws.adapter.PawsTopColAdapter
import com.example.scooby.scooby.paws.adapter.PetsShelterAdapter
import com.example.scooby.scooby.paws.adapter.ShelterAdapter
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel

class PawsFragment : Fragment() {
    private val pawsViewModel by viewModels<PawsViewModel>()
    private lateinit var binding : FragmentPawsBinding
    private lateinit var currentUserId: String
    private lateinit var rvTopCol: RecyclerView
    private lateinit var rvCats: RecyclerView
    private lateinit var rvDogs: RecyclerView
    private lateinit var rvAdoptMe: RecyclerView
    private lateinit var rvShelters: RecyclerView
    private lateinit var rvPetsShelter: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPawsBinding.inflate(inflater, container, false)
        currentUserId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
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


    }

    private fun initButtonCallBack() {
        binding.btnAdaption.setOnClickListener {
            binding.adaptionFragment.visibility = View.VISIBLE
            binding.rescueFragment.visibility = View.GONE
        }
        binding.btnRescue.setOnClickListener {
            binding.rescueFragment.visibility = View.VISIBLE
            binding.adaptionFragment.visibility = View.GONE
        }
        binding.btnMissing.setOnClickListener {

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

    private fun stopLoading() {
        binding.apply {
            loading.visibility = View.GONE
        }
    }

}