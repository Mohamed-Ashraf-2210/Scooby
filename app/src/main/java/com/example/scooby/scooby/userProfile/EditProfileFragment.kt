package com.example.scooby.scooby.userProfile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
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
import java.io.File
import java.io.IOException


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


    private fun init() {
        binding?.apply {
            backProfile.setOnClickListener {
                findNavController().popBackStack()
            }
            editImageProfile.setOnClickListener {
                pickImage()
            }
        }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        launchSomeActivity.launch(Intent.createChooser(intent, "Select Picture"))
    }


    private val launchSomeActivity =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                if (data != null && data.data != null) {
                    val selectedImageUri: Uri = data.data!!
                    val selectedImageBitmap: Bitmap?
                    try {
                        selectedImageBitmap =
                            MediaStore.Images.Media.getBitmap(
                                requireActivity().contentResolver,
                                selectedImageUri
                            )
                        saveBitmapToFile(selectedImageBitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

    private fun saveBitmapToFile(bitmap: Bitmap): File {
        val file = File(requireContext().cacheDir, "image.png")
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
        return file
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