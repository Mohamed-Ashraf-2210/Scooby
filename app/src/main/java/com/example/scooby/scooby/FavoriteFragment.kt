package com.example.scooby.scooby

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Constant

import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentFavoriteBinding

import com.example.scooby.scooby.product.adapter.FavoriteProductAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var userId: String
    private lateinit var rvFavPets : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        init()
        return binding.root
    }

    private fun init() {


        productViewModel.getFavoriteProduct(userId)
        productViewModel.favoriteProductResult.observe(viewLifecycleOwner){
            val adapter = FavoriteProductAdapter(it,productViewModel,userId)
            binding.rvFavProduct.adapter = adapter
        }
    }

}