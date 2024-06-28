package com.example.scooby.scooby.addPet

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.pet.AddPetData
import com.example.scooby.databinding.FragmentSubmitPetBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewModels.PetsViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * last screen to Add Pet
 * Add user pet image and Submit
 * author: Mohamed Ashraf
 * */
class SubmitPetFragment : Fragment() {
    private var binding: FragmentSubmitPetBinding? = null
    private lateinit var petsViewModel: PetsViewModel
    private val args: SubmitPetFragmentArgs by navArgs()
    private var selectedImg: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null)
            return binding?.root

        binding = FragmentSubmitPetBinding.inflate(inflater, container, false)
        petsViewModel = ViewModelProvider(this)[PetsViewModel::class.java]
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            addImageCard.setOnClickListener {
                pickImage()
            }
            submitBtn.setOnClickListener {
                clickToSubmit()
            }
            backScreen.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    // region pick image
    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            binding?.imagePet?.setImageBitmap(imgBitMap)
            selectedImg = saveBitmapToFile(imgBitMap)
        } else if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            binding?.imagePet?.setImageURI(imageUri)
            selectedImg = binding?.imagePet?.drawable?.let { saveBitmapToFile(it.toBitmap()) }

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

    private fun clickToSubmit() {
        if (selectedImg != null) {
            val petData = AddPetData(
                name = args.listOfData[0],
                type = args.listOfData[1],
                birthday = args.listOfData[5],
                category = args.listOfData[2],
                adoptionDay = args.listOfData[6],
                size = args.listOfData[3],
                gender = args.listOfData[4],
                profileBio = args.listOfData[7]
            )
            petsViewModel.apply {
                addPet(petData, selectedImg)
                addPetsResult.observe(viewLifecycleOwner) {
                    when (it) {
                        is BaseResponse.Loading -> {
                            showLoading()
                        }

                        is BaseResponse.Success -> {
                            stopLoading()
                            showToast("Add is successful")
                            findNavController().popBackStack()
                        }

                        is BaseResponse.Error -> {
                            stopLoading()
                            showToast(it.msg)
                        }

                        else -> {
                            stopLoading()
                        }
                    }
                }
            }
        } else {
            showToast("Select your pet image")
        }
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

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 100
        private const val REQUEST_IMAGE_PICKER = 101
    }
}