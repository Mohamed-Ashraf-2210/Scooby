package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.profile.UserProfileResponse
import com.example.scooby.R
import com.example.scooby.databinding.FragmentProfileBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.adapter.MyPetsHomeAdapter
import com.example.scooby.scooby.viewModels.ProfileViewModel
import com.example.scooby.utils.BaseResponse

/**
 * User Profile screen
 * User information and his pets
 * author: Mohamed Ashraf
 * */
class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding != null) return binding?.root

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        initView()

        return binding?.root
    }


    private fun initView() {
        clickListener()
        observeUserData()

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

    private fun dataSuccess(data: UserProfileResponse?) {
        if (data != null) {
            val userInfo = data.data.data
            binding?.apply {
                userName.text = userInfo.name
                numberFollowersTv.text = userInfo.followers.size.toString()
                numberFollowingTv.text = userInfo.following.size.toString()
                Glide.with(requireContext())
                    .load(userInfo.profileImage)
                    .placeholder(R.drawable.user_default_image)
                    .error(R.drawable.error)
                    .into(userImage)
                myPetsRv.adapter = MyPetsHomeAdapter(data, requireContext())
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

    private fun clickListener() {
        binding?.apply {

            backProfile.setOnClickListener {
                findNavController().popBackStack()
            }

            editProfile.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }

            addPetIcon.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_myPetsFragment)
            }

            favoritesSection.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment_to_favoriteFragment)
            }

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

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.myPetsRv?.adapter = null
        binding = null
    }
}
