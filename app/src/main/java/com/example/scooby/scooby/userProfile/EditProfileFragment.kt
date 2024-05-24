package com.example.scooby.scooby.userProfile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.Constant
import com.example.domain.profile.UserProfileResponse
import com.example.data.local.TokenManager
import com.example.scooby.databinding.FragmentEditProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewmodel.ProfileViewModel
import java.io.File
import java.io.FileOutputStream


class EditProfileFragment : Fragment() {
    private var binding: FragmentEditProfileBinding? = null
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String
    private var selectedImg: File? = null
    private val REQUEST_IMAGE_CAPTURE = 100
    private val REQUEST_IMAGE_PICKER = 101
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater)
        userId = TokenManager.getAuth(Constant.USER_ID).toString()
        getUserData()
        init()
        return binding?.root
    }


    // region get user data
    private fun getUserData() {
        profileViewModel.apply {
            //getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                stopLoading()
                //getProfileData(it)
            }
        }
    }

    private fun getProfileData(it: UserProfileResponse?) {
        val data = it?.data?.data
        data.let {
            binding?.apply {
                Glide.with(this@EditProfileFragment).load(data?.profileImage).into(profileImage)
                nameEditText.setText(data?.name)
                emailEditText.setText(data?.email)
            }
        }
    }
    // endregion

    private fun init() {
        binding?.apply {
            backProfile.setOnClickListener { findNavController().popBackStack() }
            editImageProfile.setOnClickListener { pickImage() }
            savaEditProfile.setOnClickListener { savaUpdate() }
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
            val imgFile = saveBitmapToFile(imgBitMap)
            selectedImg = imgFile
        }else if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            binding?.profileImage?.setImageURI(imageUri)
            if (imageUri != null) {
                val file = saveImageToFile(imageUri)
                selectedImg = file
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): File {
        val file = File(requireContext().cacheDir, "image.png")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        return file
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
    // endregion

    // region save update
    private fun savaUpdate() {
        profileViewModel.apply {
            updateUser(userId, selectedImg!!)
            updateUserResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    Toast.makeText(requireContext(), it.status, Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }
    // endregion

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
        }
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