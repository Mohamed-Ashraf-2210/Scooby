package com.example.scooby.scooby.ui

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.scooby.MainActivity
import com.example.scooby.databinding.FragmentEditProfileBinding
import com.example.scooby.scooby.data.model.ProfileDetailsResponse
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import com.example.scooby.utils.Constant
import com.example.scooby.utils.TokenManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String
    private val REQUEST_IMAGE_PICKER = 101
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater)
        init()

        binding.editImageProfile.setOnClickListener {
            pickImage()
        }
        return binding.root
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            if (imageUri != null) {
                val file = saveImageToFile(imageUri)
//                val requestFile: RequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
//                val profileImagePart = MultipartBody.Part.createFormData("profileImage", file.name, requestFile)
                profileViewModel.apply {
                    uploadImage(userId,file)
                    profileResult.observe(viewLifecycleOwner) {
                        getUserData()
                    }
                }
            } else {
                Log.e(Constant.MY_TAG, "imageUri is Null")
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun saveImageToFile(imageUri: Uri): File {
        val inputStream = imageUri.let { requireContext().contentResolver.openInputStream(it) }
        val file = File(context?.cacheDir, "selected_image.png")
        val outputStream = FileOutputStream(file)

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return file
    }


    private fun init() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        getUserData()
        backOffFragment()
    }

    private fun getUserData() {
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                getProfileData(it)
            }
        }
    }

    private fun getProfileData(it: ProfileDetailsResponse?) {
        val data = it?.data?.data
        Glide.with(this).load(data?.profileImage).into(binding.profileImage)
        binding.nameEditText.setText(data?.name)
        binding.emailEditText.setText(data?.email)
    }

    private fun backOffFragment() {
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as MainActivity).showBottomNavigationView()
    }
}