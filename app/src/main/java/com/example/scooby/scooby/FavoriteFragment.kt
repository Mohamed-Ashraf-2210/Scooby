package com.example.scooby.scooby

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.Constant
import com.example.domain.paws.AdaptionAdoptMeResponse
import com.example.domain.product.ProductResponse
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentFavoriteBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.scooby.product.adapter.FavoritePetsAdapter
import com.example.scooby.scooby.product.adapter.FavoriteProductAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.BaseResponse


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
        currentUserId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
//        setupFavoriteViews()
        init()
        return binding.root
    }

    private fun setupFavoriteViews() {
        binding.rvFavProduct.setOnClickListener { toggleFavoriteView(true) }
        binding.rvFavPets.setOnClickListener { toggleFavoriteView(false) }
        observeFavoritePets()
        observeFavoriteProduct()
    }

    private fun toggleFavoriteView(showProduct: Boolean) {
        binding.rvFavProduct.visibility = if (showProduct) View.VISIBLE else View.GONE
        binding.rvFavPets.visibility = if (showProduct) View.GONE else View.VISIBLE
    }

    private fun observeFavoritePets() {
        pawsViewModel.getFavoritePet(currentUserId)
        pawsViewModel.favoritePetResult.observe(viewLifecycleOwner) { Log.d("petsResult", it.data.toString())
            val adapter = FavoritePetsAdapter(it, pawsViewModel, currentUserId)
            binding.rvFavPets.adapter = adapter
            binding.numOfItem.text = adapter.itemCount.toString() }
    }

    private fun observeFavoriteProduct() {
        productViewModel.getFavoriteProduct(currentUserId)
        productViewModel.favoriteProductResult.observe(viewLifecycleOwner) { Log.d("productResult", it.data.toString())
            val adapter = FavoriteProductAdapter(it, productViewModel, currentUserId)
            binding.rvFavProduct.adapter = adapter
            binding.numOfItem.text = adapter.itemCount.toString() }
    }



    private fun init() {

        binding.btnFavProduct.setOnClickListener {
            binding.rvFavPets.visibility = View.GONE
            binding.rvFavProduct.visibility = View.VISIBLE
        }
        binding.btnFavPets.setOnClickListener {
            binding.rvFavProduct.visibility = View.GONE
            binding.rvFavPets.visibility = View.VISIBLE
        }
        observeFavoritePets()
        observeFavoriteProduct()


    }

}