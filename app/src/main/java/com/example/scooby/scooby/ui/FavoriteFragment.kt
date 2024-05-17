package com.example.scooby.scooby.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.Constant
import com.example.data.local.TokenManager
import com.example.scooby.databinding.FragmentFavoriteBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.scooby.product.adapter.FavoritePetsAdapter
import com.example.scooby.scooby.product.adapter.FavoriteProductAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val pawsViewModel by viewModels<PawsViewModel>()
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var currentUserId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        currentUserId = TokenManager.getAuth(Constant.USER_ID).toString()
        init()
        return binding.root
    }

    private fun observeFavoriteProduct() {
        productViewModel.getFavoriteProduct(currentUserId)
        productViewModel.favoriteProductResult.observe(viewLifecycleOwner) {
            Log.d("productResult", it.data.toString())
            val adapter = FavoriteProductAdapter(it, productViewModel, currentUserId)
            binding.rvFavProduct.adapter = adapter
            binding.numOfItem.text = it.data.size.toString()

        }
    }
    private fun observeFavoritePets() {
        pawsViewModel.getFavoritePet(currentUserId)
        pawsViewModel.favoritePetResult.observe(viewLifecycleOwner) {
            Log.d("petsResult", it.data.toString())
            val adapter = FavoritePetsAdapter(it, pawsViewModel, currentUserId)
            binding.rvFavPets.adapter = adapter
            binding.numOfItem.text = adapter.itemCount.toString()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        observeFavoriteProduct()
        binding.btnFavProduct.setOnClickListener {
            binding.rvFavPets.visibility = View.GONE
            binding.rvFavProduct.visibility = View.VISIBLE
            binding.tvItems.text = "Items"
            observeFavoriteProduct()
        }
        binding.btnFavPets.setOnClickListener {
            binding.rvFavProduct.visibility = View.GONE
            binding.rvFavPets.visibility = View.VISIBLE
            binding.tvItems.text = "Babies"
            observeFavoritePets()
        }
    }
}