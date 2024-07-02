package com.example.scooby.scooby.userProfile

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
import com.bumptech.glide.Glide
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentEditProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewModels.ProfileViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class EditProfileFragment : Fragment() {
    private var binding: FragmentEditProfileBinding? = null
    private lateinit var profileViewModel: ProfileViewModel
    private var selectedImg: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) return binding?.root

        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        initView()
        selectedImg = binding?.profileImage?.drawable?.let { saveBitmapToFile(it.toBitmap()) }

        return binding?.root
    }


    private fun initView() {
        changeScreen()
        observeUserData()

    }

    private fun changeScreen() {
        binding?.apply {
            backProfile.setOnClickListener { findNavController().popBackStack() }
            editImageProfile.setOnClickListener { pickImage() }
            savaEditProfile.setOnClickListener {
                observeUpdateUserImage()
            }
        }
    }


    private fun observeUserData() {
        profileViewModel.apply {
            getUserInfo()
            profileResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        dataSuccess(it.data)
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
    }


    private fun observeUpdateUserImage() {
        profileViewModel.apply {
            binding?.apply {
                selectedImg = saveBitmapToFile(profileImage.drawable.toBitmap())
                updateUser(nameEditText.text.toString(), emailEditText.text.toString(), selectedImg)

            }
            updateUserResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        showToast("Success")
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
            binding?.profileImage?.setImageBitmap(imgBitMap)
        } else if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            binding?.profileImage?.setImageURI(imageUri)
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


    private fun dataSuccess(data: UserProfileResponse?) {
        if (data != null) {
            val userInfo = data.data.data
            binding?.apply {
                nameEditText.setText(userInfo.name)
                emailEditText.setText(userInfo.email)
                Glide.with(requireContext())
                    .load(userInfo.profileImage)
                    .placeholder(R.drawable.user_default_image)
                    .error(R.drawable.error)
                    .into(profileImage)
            }
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