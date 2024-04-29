package com.example.scooby.scooby.product.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Constant
import com.example.domain.product.ProductResponse
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentProductBinding
import com.example.scooby.scooby.product.adapter.ProductAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel

class ProductFragment : Fragment() {
    private lateinit var allProduct: ProductResponse
    private lateinit var favoriteProducts: ProductResponse
    private val productViewModel by viewModels<ProductViewModel>()
    private lateinit var productAdapter : ProductAdapter
    private lateinit var userId: String
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private lateinit var productRv: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        init()
        return binding.root
    }



    private fun filterProduct(productType: String) {
        productViewModel.apply {
            val filterProductByType = allProduct.data.filter { it.category == productType }
            getProductData(ProductResponse(filterProductByType), favoriteProducts)

        }

    }

    private fun init() {
        observeOfferViewModel()
        backOffFragment()
        callBackButton()
    }

    private fun callBackButton() {
        binding.btnMedicine.setOnClickListener {
            filterProduct("medicine")
        }
        binding.btnFood.setOnClickListener {
            filterProduct("food")
        }
        binding.btnToys.setOnClickListener {
            filterProduct("toys")
        }
        binding.btnAccessories.setOnClickListener {
            filterProduct("accessories")
        }
        binding.btnGrooming.setOnClickListener {
            filterProduct("grooming")
        }
        binding.cart.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_favoriteFragment)
        }
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
            productResult.observe(viewLifecycleOwner) { products ->
                getFavoriteProduct(userId)
                favoriteProductResult.observe(viewLifecycleOwner) { _favoriteProducts ->
                    favoriteProducts = _favoriteProducts
                    stopLoading()
                    getProductData(products, favoriteProducts)
                    allProduct = products!!
                    Log.d("my_Tagg", products.data.toString())
                }
            }
        }
    }


    private fun getProductData(data: ProductResponse?, favoriteProducts: ProductResponse) {
        productRv = binding.productRv
        productRv.adapter = ProductAdapter(productViewModel, userId, data!!, favoriteProducts)
    }
    private fun setData(productListDiff:List<ProductResponse.Data>){
        productAdapter.setData(productListDiff)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.productRv.adapter = null
    }
    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
        binding.productRv.visibility = View.VISIBLE
    }
    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.productRv.visibility = View.VISIBLE
    }
    private fun backOffFragment() {
        binding?.icBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}