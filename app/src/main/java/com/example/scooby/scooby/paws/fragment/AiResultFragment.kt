package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentAiResultBinding
import com.example.scooby.utils.loadUrl
import okhttp3.internal.wait

class AiResultFragment : Fragment() {
    private val args : AiResultFragmentArgs by navArgs()
    private var _binding: FragmentAiResultBinding?= null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAiResultBinding.inflate(layoutInflater, container, false)
        initView()
        // Inflate the layout for this fragment
        callBackImgBtn()
        return binding.root
    }

    private fun callBackImgBtn() {
        binding.foundPetImg1.setOnClickListener {
//            findNavController().navigate(R.id.action_aiResultFragment_to_userProfileMomentFragment)
            sendId2UserProfile()
        }
    }

    private fun sendId2UserProfile() {
        val action = AiResultFragmentDirections.actionAiResultFragmentToUserProfileMomentFragment(args.userId1)
        findNavController().navigate(action)
    }

    private fun initView() {
        binding.MyUploadedImg.loadUrl(args.uploadedImg)
        binding.petCurrentOwn.text = args.name1
        binding.foundPetImg1.loadUrl(args.petImg1)
        binding.petDesc.text = args.desc1
        binding.petOwnerNm.text = args.phoneNum1
        binding.petOwnerLoc.text = args.location1
        binding.petCurrentOwn2.text = args.name2
        binding.foundPetImg2.loadUrl(args.petImg2)
        binding.petDesc2.text = args.desc2
        binding.petOwnerNm2.text = args.phoneNum2
        binding.petOwnerLoc2.text = args.location2

    }


}