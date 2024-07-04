package com.example.scooby.scooby.userProfile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentMomentBinding
import com.example.scooby.databinding.FragmentReviewBinding
import com.example.scooby.scooby.adapter.UserMomentAdapter
import com.example.scooby.scooby.adapter.UserReviewAdapter
import com.example.scooby.scooby.viewModels.CommunityViewModel
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.SharedViewModel

class ReviewFragment : Fragment() {
    private val args : ReviewFragmentArgs by navArgs()
    private lateinit var communityViewModel: CommunityViewModel
    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReviewBinding.inflate(layoutInflater,container,false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
        // Inflate the layout for this fragment
        //init()
        return binding.root
    }

    private fun init() {
        observableOnReview()
    }

    @SuppressLint("SetTextI18n")
    private fun observableOnReview() {
        Log.i("checkUserId","in Review "+sharedViewModel.userId)
        communityViewModel.getUserReview(sharedViewModel.userId)
        communityViewModel.apply {
            userReviewResult.observe(viewLifecycleOwner){
                when(it){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        Log.i("InfoPost", it.data.toString())
                        if(it.data?.data?.reviews.isNullOrEmpty()){
                            binding.textview.visibility = View.VISIBLE
                            binding.rvReview.visibility = View.GONE
                            binding.textview.text = "There is no reviews.."
                        }else{
                            binding.textview.visibility = View.GONE
                            binding.rvReview.visibility = View.VISIBLE
                            binding.rvReview.adapter = it.data?.let { reviewRes -> UserReviewAdapter(reviewRes) }
                        }

                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast("error in Review ")
                    }
                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }
    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

}