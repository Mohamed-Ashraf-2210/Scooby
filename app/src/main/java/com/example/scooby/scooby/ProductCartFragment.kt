package com.example.scooby.scooby

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.Constant
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentProductCartBinding
import com.example.scooby.scooby.product.viewmodel.ProductViewModel

class ProductCartFragment : Fragment() {
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var binding: FragmentProductCartBinding
    private lateinit var currentUserId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCartBinding.inflate(layoutInflater,container,false)
        currentUserId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        initRvCart()
        observeUserCart()
        return binding.root
    }

    private fun observeUserCart() {
        productViewModel.getCartUser(currentUserId)
        productViewModel.userCartResult.observe(viewLifecycleOwner){
            val adapter =  ProductCartAdapter(it,currentUserId)
            binding.rvCart.adapter = adapter
            Log.d("Cart User", it.data.toString())
        }
    }

    private fun initRvCart() {

    }


}