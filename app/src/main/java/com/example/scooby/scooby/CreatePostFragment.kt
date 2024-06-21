package com.example.scooby.scooby

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.scooby.databinding.FragmentCreatePostBinding
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreatePostFragment : Fragment() {
    private var binding : FragmentCreatePostBinding?= null
    private lateinit var pawsViewModel: PawsViewModel
    private lateinit var profileViewModel: ProfileViewModel
    private var selectedImg: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater,container,false)
        pawsViewModel = ViewModelProvider(this)[PawsViewModel::class.java]
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        selectedImg = binding?.petImage?.drawable?.let { saveBitmapToFile(it.toBitmap()) }
        init()
        return binding!!.root
    }

    private fun init() {
        callBackSelectImage()
        observeData()
    }
    private fun callBackSelectImage() {
        binding?.postBtn?.setOnClickListener {
            observeData()
        }
        binding?.cameraBtn?.setOnClickListener {
            pickImage()
        }
    }

    private fun observeData() {
        pawsViewModel.apply {
                binding?.apply {
                    selectedImg = petImage.drawable?.toBitmap()?.let { saveBitmapToFile(it) }
                    foundPet(selectedImg, descEditText.editText.toString())
                }
                iFoundPetResult.observe(viewLifecycleOwner) {
                    when (it) {
                        is BaseResponse.Loading -> {
                            showLoading()
                        }
                        is BaseResponse.Success -> {
                            stopLoading()
                            showToast("Send is successful")
                        }
                        is BaseResponse.Error -> {
                            stopLoading()
                            showToast("Erorr")
                        }
                        else -> {
                            stopLoading()
                        }
                    }
                }
        }
    }


    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CreatePostFragment.REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            binding?.petImage?.setImageBitmap(imgBitMap)
        } else if (requestCode == CreatePostFragment.REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            binding?.petImage?.setImageURI(imageUri)
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
    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 100
        private const val REQUEST_IMAGE_PICKER = 101
    }

}