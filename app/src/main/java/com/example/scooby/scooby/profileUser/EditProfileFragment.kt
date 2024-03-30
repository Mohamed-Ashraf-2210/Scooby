package com.example.scooby.scooby.profileUser

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.File
import java.io.FileOutputStream


class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String
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
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .galleryMimeTypes(  //Exclude gif images
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            .start()

        startForProfileImageResult
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data
            if (resultCode == Activity.RESULT_OK) { // requestCode == REQUEST_IMAGE_PICKER &&
                val imageUri = data?.data!!
                binding.editImageProfile.setImageURI(imageUri)
                val file = saveImageToFile(imageUri)
//                profileViewModel.apply {
//                    uploadImage(userId, file)
//                    profileResult.observe(viewLifecycleOwner) {
//                        getUserData()
//                    }
//                }
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
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }
}