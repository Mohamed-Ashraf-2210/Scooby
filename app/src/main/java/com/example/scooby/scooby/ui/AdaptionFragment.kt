package com.example.scooby.scooby.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.paws.AdaptionCatsResponse
import com.example.domain.paws.AdaptionDogsResponse
import com.example.domain.paws.AdaptionResponse
import com.example.scooby.databinding.FragmentAdaptionBinding
import com.example.scooby.scooby.adapter.AdaptionAdoptMeAdapter
import com.example.scooby.scooby.adapter.AdaptionCatsAdapter
import com.example.scooby.scooby.adapter.AdaptionDogsAdapter
import com.example.scooby.scooby.adapter.PawsTopColAdapter
import com.example.scooby.scooby.viewmodel.PawsViewModel


class AdaptionFragment : Fragment() {
    private val pawsViewModel by viewModels<PawsViewModel>()
    private lateinit var binding: FragmentAdaptionBinding
    private lateinit var rvTopCol: RecyclerView
    private lateinit var rvCats: RecyclerView
    private lateinit var rvDogs: RecyclerView
    private lateinit var rvAdoptMe: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdaptionBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        init()
        return binding.root
    }

    private fun init() {
        observeOfferViewModel()
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

    }

    private fun getDataTopCollection(data: AdaptionResponse) {
        rvTopCol = binding.rvTopCol
        rvTopCol.adapter = PawsTopColAdapter(data)
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
        rvAdoptMe.adapter = AdaptionAdoptMeAdapter(data)
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
        }
    }


}