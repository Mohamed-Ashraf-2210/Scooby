package com.example.scooby.scooby.product.fragment

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.local.TokenManager
import com.example.data.utils.Constant
import com.example.domain.product.ProductResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentProductBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.product.adapter.ProductAdapter
import com.example.scooby.scooby.product.viewmodel.ProductViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProductFragment : Fragment() {
    private lateinit var allProduct: ProductResponse
    private lateinit var favoriteProducts: ProductResponse
    private lateinit var productViewModel: ProductViewModel
    private var binding: FragmentProductBinding? = null
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
    private val REQUEST_IMAGE_CAPTURE = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(layoutInflater, container, false)
        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        init()

        binding?.cameraBtn?.setOnClickListener { takeImage() }
        return binding?.root
    }

    // region Take Image to OCR
    private fun takeImage() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${e.localizedMessage}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            val imgFile = saveBitmapToFile(imgBitMap)
            showToast("Camera Success")
            productViewModel.apply {
                sendImageToOCR(imgFile)
                getFavoriteProduct()
                ocrResult.observe(viewLifecycleOwner) { products ->
                    when (products) {
                        is BaseResponse.Loading -> {
                            showLoading()
                        }

                        is BaseResponse.Success -> {
                            stopLoading()
                            showToast("Success")
                            favoriteProductResult.observe(viewLifecycleOwner) {
                                favoriteProducts = it
                                stopLoading()
                                getProductData(products.data, favoriteProducts)
                                allProduct = products.data!!
                            }
                        }

                        is BaseResponse.Error -> {
                            stopLoading()
                            showToast(products.msg)
                        }

                        else -> {
                            stopLoading()
                        }
                    }
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): String? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timeStamp.jpg"
        val storageDir = requireContext().getExternalFilesDir(null)

        val file = File(storageDir, fileName)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()
            return file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fileOutputStream?.close()
        }
        return null
    }
    // endregion


    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun filterProduct(productType: String) {
        productViewModel.apply {
            val filterProductByType = allProduct.data.filter { it.category == productType  }
            getProductData(ProductResponse(filterProductByType,85,"success"), favoriteProducts)
        }
    }

    private fun init() {
        observeProductViewModel()
        backOffFragment()
        callBackButton()
    }

    private fun callBackButton() {
        binding?.apply {
            btnMedicine.setOnClickListener {
                filterProduct("medicine")
            }
            btnFood.setOnClickListener {
                filterProduct("food")
            }
            btnToys.setOnClickListener {
                filterProduct("toys")
            }
            btnAccessories.setOnClickListener {
                filterProduct("accessories")
            }
            btnGrooming.setOnClickListener {
                filterProduct("grooming")
            }
            cart.setOnClickListener {
                findNavController().navigate(R.id.action_productFragment_to_productCartFragment)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.refreshMedicineLayout?.apply {
            setOnRefreshListener {
                isRefreshing = false
            }
        }
    }

    private fun observeProductViewModel() {
        productViewModel.apply {
            getProduct()
            getFavoriteProduct()

            productResult.observe(viewLifecycleOwner) { products ->

                when (products) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        //stopLoading()
                        favoriteProductResult.observe(viewLifecycleOwner) {
                            favoriteProducts = it
                            stopLoading()
                            getProductData(products.data, favoriteProducts)
                            allProduct = products.data!!
                        }
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        // showToast()
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }


    }


    private fun getProductData(data: ProductResponse?, favoriteProducts: ProductResponse) {
        binding?.productRv?.adapter =
            ProductAdapter(productViewModel, data!!, favoriteProducts)
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            productRv.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding?.apply {
            loading.visibility = View.VISIBLE
            productRv.visibility = View.VISIBLE
        }
    }

    private fun backOffFragment() {
        binding?.icBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}