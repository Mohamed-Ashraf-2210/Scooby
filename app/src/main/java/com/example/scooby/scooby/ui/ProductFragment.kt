package com.example.scooby.scooby.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.product.ProductResponse
import com.example.scooby.databinding.FragmentProductBinding
import com.example.scooby.scooby.adapter.ProductAdapter
import com.example.scooby.scooby.viewmodel.OfferViewModel
import com.example.scooby.scooby.viewmodel.ProductViewModel

class ProductFragment : Fragment() {
    private lateinit var allProduct : ProductResponse
    private val productViewModel by viewModels<ProductViewModel>()

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var productRv : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(layoutInflater,container,false)
        init()
        observeOfferViewModel()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshMedicineLayout.apply {
            setOnRefreshListener {
                isRefreshing = false
            }
        }
    }
    private fun observeOfferViewModel() {
        productViewModel.apply {
            getProduct()
            productResult.observe(viewLifecycleOwner){
                getProductData(it)
                allProduct = it!!
                Log.d("my_Tagg",it.data.toString())

            }
        }

    }

    private fun init() {

    }

    private fun getProductData(data : ProductResponse?){
        productRv = binding.productRv
        productRv.adapter = ProductAdapter(data!!,requireContext())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}