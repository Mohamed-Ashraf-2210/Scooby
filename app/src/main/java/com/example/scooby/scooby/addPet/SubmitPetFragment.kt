package com.example.scooby.scooby.addPet

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.pet.AddPetData
import com.example.scooby.databinding.FragmentSubmitPetBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewmodel.PetsViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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


    private var imagePet: Bitmap? = null

    private val launchSomeActivity =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                // do your operation from here....
                if (data != null && data.data != null) {
                    val selectedImageUri: Uri = data.data!!
                    val selectedImageBitmap: Bitmap?
                    try {
                        selectedImageBitmap =
                            MediaStore.Images.Media.getBitmap(
                                requireActivity().contentResolver,
                                selectedImageUri
                            )
                        binding?.imagePet?.setImageBitmap(selectedImageBitmap)
                        imagePet = selectedImageBitmap
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }


    private fun bitmapToFile(bitmap: Bitmap?): File {
        val file = File(requireActivity().cacheDir, "temp_image.jpg")
        file.createNewFile()
        val outputStream = FileOutputStream(file)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return file
    }

    // this function is triggered when
    // the Select Image Button is clicked
    private fun imageChooser() {
        // create an instance of the
        // intent of the type image
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.type = "image/*"
        // pass the constant to compare it
        // with the returned requestCode
        launchSomeActivity.launch(Intent.createChooser(i, "Select Picture"))
    }

    private fun initView() {
        binding?.apply {
            addImageCard.setOnClickListener {
                imageChooser()
            }
            submitBtn.setOnClickListener {
                clickToSubmit()
            }
            backScreen.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


    private fun clickToSubmit() {
        if (imagePet != null) {

            val petData = AddPetData(
                name = args.listOfData?.get(0) ?: "",
                type = args.listOfData?.get(1) ?: "",
                birthday = args.listOfData?.get(5) ?: "",
                breed = args.listOfData?.get(2) ?: "",
                adoptionDay = args.listOfData?.get(6) ?: "",
                size = args.listOfData?.get(3) ?: "",
                gender = args.listOfData?.get(4) ?: "",
                profileBio = args.listOfData?.get(7) ?: ""
            )
            val file = bitmapToFile(imagePet)
            petsViewModel.apply {
                addPet(petData,file.absolutePath)
                addPetsResult.observe(viewLifecycleOwner) {
                    when (it) {
                        is BaseResponse.Loading -> {
                            //showLoading()
                        }

                        is BaseResponse.Success -> {
                            //stopLoading()
                            showToast("Save is successful")
                        }

                        is BaseResponse.Error -> {
                            //stopLoading()
                            showToast(it.msg)
                        }

                        else -> {
                            //stopLoading()
                        }
                    }
                }
            }
//            findNavController().popBackStack()
        }

    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
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
}