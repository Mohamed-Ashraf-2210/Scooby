package com.example.scooby.scooby

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.domain.CartProductResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentCheckoutPaymentBinding
import com.example.scooby.databinding.FragmentFailedUploadBinding
import com.example.scooby.scooby.adapter.UserMomentAdapter
import com.example.scooby.scooby.product.adapter.CheckoutPaymentAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.BaseResponse


class CheckoutPaymentFragment : Fragment() {
    private var _binding: FragmentCheckoutPaymentBinding? = null
    private val binding get() = _binding!!
    private lateinit var productViewModel: ProductViewModel
    private var paymentMethod: String = "Cash"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCheckoutPaymentBinding.inflate(layoutInflater,container,false)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        init()
        return binding.root
    }

    private fun init() {
        observeViewModel()
        callBackButton()
    }

    private fun callBackButton() {
        binding.apply {
            radioGroup.setOnCheckedChangeListener{_, checkedId ->
                when (checkedId) {
                    R.id.cashBtn -> {
                        paymentMethod = "Cash"
                        payOnBtn.visibility = View.GONE
                        placeOrderBtn.visibility = View.VISIBLE
                    }

                    R.id.onlineBtn -> {
                        payOnBtn.visibility = View.VISIBLE
                        placeOrderBtn.visibility = View.GONE
                    }
                }
            }
        }

        binding.placeOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutPaymentFragment_to_orderSuccessFragment)
        }
    }

    private fun observeViewModel() {
        productViewModel.apply {
            getCartPayment()
            productViewModel.cartResult.observe(viewLifecycleOwner){
                when (it) {
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        Log.i("InfoCart", it.data.toString())
                        binding.apply {
                            itemInCart.text = it.data?.data?.cartItems?.size.toString()
                            priceSubtotal.text = it.data?.data?.totalCartPrice.toString()
                            priceTotal2.text = it.data?.data?.totalCartPrice.toString()
                        }
                        binding.rvOrderSummary.adapter = it.data?.let { it1 ->
                            CheckoutPaymentAdapter(
                                it1
                            )
                        }
                        it.data?.data?.id?.let { it1 -> getCheckoutSession(it1) }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast("error in checkoutPayment ")
                    }
                    else -> {
                        stopLoading()
                    }
                }
            }
        }

        productViewModel.apply {
            checkoutSessionResult.observe(viewLifecycleOwner){
                when (it) {
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        Log.i("InfoCart", it.data.toString())
                        Log.i("InfoUrl", it.data?.session?.url.toString())
                        val url = it.data?.session?.url.toString()
                        binding.payOnBtn.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(url)
                            startActivity(intent)
                        }
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast("error in checkoutSession ")
                    }
                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }
    fun bindData2Ui(product: CartProductResponse.Data){
        binding.apply {

        }

    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }


}