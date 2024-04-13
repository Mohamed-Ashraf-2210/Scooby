package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.Constant
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentEditProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewmodel.ProfileViewModel


class EditProfileFragment : Fragment() {
    private var binding: FragmentEditProfileBinding? = null
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var userId: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater)
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
        getUserData()
        init()
        return binding?.root
    }

//    private fun pickImage() {
//        ImagePicker.with(this)
//            .crop()                    //Crop image(Optional), Check Customization for more option
//            .compress(1024)            //Final image size will be less than 1 MB(Optional)
//            .maxResultSize(
//                1080,
//                1080
//            )    //Final image resolution will be less than 1080 x 1080(Optional)
//            .galleryMimeTypes(  //Exclude gif images
//                mimeTypes = arrayOf(
//                    "image/png",
//                    "image/jpg",
//                    "image/jpeg"
//                )
//            )
//            .start()
//
//        startForProfileImageResult
//    }

//    private val startForProfileImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//            if (resultCode == Activity.RESULT_OK) { // requestCode == REQUEST_IMAGE_PICKER &&
//                val imageUri = data?.data!!
//                binding.editImageProfile.setImageURI(imageUri)
//                val file = saveImageToFile(imageUri)
////                profileViewModel.apply {
////                    uploadImage(userId, file)
////                    profileResult.observe(viewLifecycleOwner) {
////                        getUserData()
////                    }
////                }
//            }
//        }


//    private fun saveImageToFile(imageUri: Uri): File {
//        val inputStream = imageUri.let { requireContext().contentResolver.openInputStream(it) }
//        val file = File(context?.cacheDir, "selected_image.png")
//        val outputStream = FileOutputStream(file)
//
//        inputStream?.use { input ->
//            outputStream.use { output ->
//                input.copyTo(output)
//            }
//        }
//        return file
//    }


    private fun init() {
        binding?.apply {
//            savaEditProfile.setOnClickListener {
//                val updatedUserData = UpdateUserData(
//                    nameEditText.text.toString(),
//                    emailEditText.text.toString(),
//                    phoneEditText.text.toString()
//                )
//                profileViewModel.apply {
//                    updateUser(userId, updatedUserData)
//                    profileResult.observe(viewLifecycleOwner) {
//                        if (it != null) {
//                            if (it.status == "success") {
//                                Toast.makeText(context, "Save is success", Toast.LENGTH_SHORT)
//                                    .show()
//                            } else {
//                                Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show()
//                            }
//
//                        }
//                    }
//                }
//            }
            backProfile.setOnClickListener {
                findNavController().popBackStack()
            }
            editImageProfile.setOnClickListener {
                //pickImage()
            }
        }
    }

    private fun getUserData() {
        profileViewModel.apply {
            getUser(userId)
            profileResult.observe(viewLifecycleOwner) {
                stopLoading()
                getProfileData(it)
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

    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            editProfileContent.visibility = View.VISIBLE
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