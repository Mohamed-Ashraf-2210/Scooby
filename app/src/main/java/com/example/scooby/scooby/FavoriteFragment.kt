package com.example.scooby.scooby

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentFavoriteBinding
import com.example.scooby.scooby.paws.adapter.AdaptionAdoptMeAdapter
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val pawsViewModel by viewModels<PawsViewModel>()
    private lateinit var rvFavPets : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init() {
        binding.btnAdoptMe.setOnClickListener {
            pawsViewModel.apply {
                getFavoritePets("66138da36320a6b3105e2ad1")
                favoriteProductResult.observe(viewLifecycleOwner){
                    getFavoritePets("66138da36320a6b3105e2ad1")
                    getAdoptMeData(it)
                    Log.d("FAV_RES", it.status.toString())
                }
            }
            binding.rvFavPets.visibility = View.VISIBLE
        }

    }

    private fun getAdoptMeData(data: AdaptionAdoptMeResponse) {
        rvFavPets = binding.rvFavPets
        rvFavPets.adapter = AdaptionAdoptMeAdapter(data)
    }
}