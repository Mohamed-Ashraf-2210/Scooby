package com.example.scooby.scooby.product.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.utils.Constant
import com.example.domain.CartProductResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentProductCartBinding
import com.example.scooby.scooby.product.adapter.ProductCartAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.IRefreshListListener
import com.example.data.local.TokenManager
import com.example.scooby.utils.BaseResponse
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductCartFragment : Fragment(), IRefreshListListener {
    private lateinit var cartResponse: CartProductResponse
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var binding: FragmentProductCartBinding
    private lateinit var currentUserId: String
    private lateinit var adapter: ProductCartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductCartBinding.inflate(layoutInflater, container, false)
        currentUserId = TokenManager.getAuth( Constant.USER_ID).toString()
        initViews()
        observeCoupon()
        observeUserCart()
        callBackBtn()
        binding.btnApply.setOnClickListener {
            productViewModel.apply {
                applyCoupon()
            }
        }
        return binding.root
    }

    private fun callBackBtn() {
        binding.checkoutBtn.setOnClickListener {
            findNavController().navigate(R.id.action_productCartFragment_to_checkoutPaymentFragment)
        }
    }


    private fun observeCoupon() {
        applyCoupon()
        productViewModel.couponResult.observe(viewLifecycleOwner){
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    Log.i("apply_coupon", "success")
                    binding.priceSubtotal.text = it.data?.data?.totalPriceAfterDiscount.toString()
                    binding.priceTotal2.text = it.data?.data?.totalPriceAfterDiscount.toString()
                    binding.priceCheckout.text = it.data?.data?.totalPriceAfterDiscount.toString()
                    binding.editTextCoupon.setText("")
                }

                is BaseResponse.Error -> {
                    stopLoading()
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }
    private fun applyCoupon() {
        binding.apply {
            val couponText = editTextCoupon.text.toString()
            productViewModel.applyCoupon(couponText)
        }
    }

    private fun initViews() {
        hideNavBar()
        adapter = ProductCartAdapter(productViewModel, viewLifecycleOwner,this)
        binding.rvCart.adapter = adapter
        getCartData()
    }

    private fun getCartData() {
            productViewModel.getCartUser()
    }
    private fun setData2Ui(cartProductResponse: CartProductResponse) {
        binding.apply {
            priceCheckout.text = cartProductResponse.data.totalCartPrice.toString()
            priceSubtotal.text = cartProductResponse.data.totalCartPrice.toString()
            priceTotal2.text = cartProductResponse.data.totalCartPrice.toString()
        }
    }
    private fun observeUserCart() {
        productViewModel.userCartResult.observe(viewLifecycleOwner) {
            binding.loading.visibility = View.GONE
            if (it != null) {
                initRecycleView(it)
                setData2Ui(it)
                Log.d("Cart User", it.data.toString())
                binding.itemCardInfo.visibility = View.VISIBLE
                productViewModel.userCartResult.value = null
            }
        }
    }

    private fun initRecycleView(data: CartProductResponse) {
        adapter.submitList(data.data.cartItems)
        binding.itemQuantity.text = adapter.itemCount.toString()
        binding.itemInCart.text = adapter.itemCount.toString()
    }

    private fun hideNavBar() {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
    }

    override fun onRefreshList() {
        binding.loading.visibility = View.VISIBLE
        getCartData()
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }
}