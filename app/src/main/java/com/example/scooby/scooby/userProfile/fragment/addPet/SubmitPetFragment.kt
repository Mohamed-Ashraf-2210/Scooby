package com.example.scooby.scooby.userProfile.fragment.addPet

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentSubmitPetBinding
import com.example.scooby.scooby.MainActivity
import java.io.IOException


class SubmitPetFragment : Fragment() {
    private var binding: FragmentSubmitPetBinding? = null
    private val args: SubmitPetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubmitPetBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }



    // constant to compare
    // the activity result code
    private val SELECT_PICTURE = 200

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
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
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
        clickToBack()
        clickToSubmit()
        binding?.addImageCard?.setOnClickListener {
            imageChooser()
        }
    }

    private fun clickToSubmit() {

    }

    private fun clickToBack() {
        binding?.backProfile?.setOnClickListener {
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