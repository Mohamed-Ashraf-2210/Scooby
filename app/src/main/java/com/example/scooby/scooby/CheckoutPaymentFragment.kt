package com.example.scooby.scooby

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
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
                        binding.rvOrderSummary.adapter = it.data?.let { it1 ->
                            CheckoutPaymentAdapter(
                                it1
                            )
                        }
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


}