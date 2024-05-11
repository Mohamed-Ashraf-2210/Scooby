package com.example.scooby.scooby.product.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.data.Constant
import com.example.domain.CartProductResponse
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentProductCartBinding
import com.example.scooby.scooby.product.adapter.ProductCartAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductCartFragment : Fragment() {
    private lateinit var cartResponse : CartProductResponse
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
        hideNavBar()
        observeUserCart()
        return binding.root
    }

    private fun setData2Ui(cartProductResponse: CartProductResponse) {
        binding.apply {
            priceCheckout.text = cartProductResponse.data.totalCartPrice.toString()
            priceSubtotal.text = cartProductResponse.data.totalCartPrice.toString()
            priceTotal2.text = cartProductResponse.data.totalCartPrice.toString()
        }
    }

    private fun observeUserCart() {
        if(currentUserId != null){
            productViewModel.getCartUser(currentUserId)
            productViewModel.userCartResult.observe(viewLifecycleOwner){
                initRecycleView(it,currentUserId)
                setData2Ui(it)
                Log.d("Cart User", it.data.toString())
                binding.itemCardInfo.visibility = View.VISIBLE
            }
        }else{
            Log.d("Cart User", "null")
        }

    }
    private fun initRecycleView(data : CartProductResponse,currentUserId: String){
        val adapter =  ProductCartAdapter(currentUserId,productViewModel,viewLifecycleOwner)
        adapter.submitList(data.data.cartItems)
        binding.rvCart.adapter = adapter
        binding.itemQuantity.text = adapter.itemCount.toString()
        binding.itemInCart.text = adapter.itemCount.toString()
    }

    private fun hideNavBar() {
        val navBar:BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
    }


}