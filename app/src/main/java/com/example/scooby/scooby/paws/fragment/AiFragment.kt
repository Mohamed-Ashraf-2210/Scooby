package com.example.scooby.scooby.paws.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.scooby.R
import com.example.scooby.databinding.FragmentAiBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.paws.viewmodel.PawsViewModel
import com.example.scooby.utils.BaseResponse
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AiFragment : Fragment() {
    private lateinit var pawsViewModel: PawsViewModel
    private var selectedImg: String? = null
    private var _binding: FragmentAiBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAiBinding.inflate(layoutInflater, container, false)
        pawsViewModel = ViewModelProvider(this)[PawsViewModel::class.java]
        selectedImg = binding.targetImg.drawable?.let { saveBitmapToFile(it.toBitmap()) }

        init()
        return binding.root
    }

    private fun init() {
        callBackSelectImage()
        observeData()
    }

    private fun callBackSelectImage() {
        binding.apply {
            sendBtn.setOnClickListener {
                selectedImg = targetImg.drawable?.toBitmap()?.let { saveBitmapToFile(it) }
                pawsViewModel.getMissingPet(selectedImg)

            }
        }
        binding.cameraBtn.setOnClickListener {
            pickImage()
        }
    }

    private fun observeData() {
        pawsViewModel.apply {
            missingPetResult.observe(viewLifecycleOwner){
                when(it){
                    is BaseResponse.Loading -> {
                        showLoading()
                    }
                    is BaseResponse.Success -> {

                        stopLoading()
                        showToast("Send is successful")
                        Log.i(
                            "CHECK_MISSING_SUCCESS",
                            it.data?.similarityArray?.get(0)?.location.toString()
                        )
                        Log.i(
                            "CHECK_MISSING_SUCCESS",
                            it.data?.similarityArray?.get(1)?.location.toString()
                        )
                        val action = AiFragmentDirections.actionAiFragmentToAiResultFragment(
                            it.data?.uploadedImage.toString(),
                            it.data?.similarityArray?.get(0)?.userId?.name.toString(),
                            it.data?.similarityArray?.get(0)?.url.toString(),
                            it.data?.similarityArray?.get(0)?.description.toString(),
                            it.data?.similarityArray?.get(0)?.phoneNumber.toString(),
                            it.data?.similarityArray?.get(0)?.location.toString(),
                            it.data?.similarityArray?.get(1)?.userId?.name.toString(),
                            it.data?.similarityArray?.get(1)?.url.toString(),
                            it.data?.similarityArray?.get(1)?.description.toString(),
                            it.data?.similarityArray?.get(1)?.phoneNumber.toString(),
                            it.data?.similarityArray?.get(1)?.location.toString(),
                            it.data?.similarityArray?.get(0)?.userId?.id.toString(),
                            it.data?.similarityArray?.get(1)?.userId?.id.toString()
                        )
                        findNavController().navigate(action)
                        findNavController().navigate(R.id.action_aiFragment_to_aiResultFragment)
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                        findNavController().navigate(R.id.action_aiFragment_to_failedUploadFragment)
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

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imgBitMap = data?.extras?.get("data") as Bitmap
            binding.targetImg.setImageBitmap(imgBitMap)
        } else if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data!!
            binding.targetImg.setImageURI(imageUri)
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

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
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
    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 100
        private const val REQUEST_IMAGE_PICKER = 101
    }

}