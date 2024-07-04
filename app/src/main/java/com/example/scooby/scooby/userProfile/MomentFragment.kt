package com.example.scooby.scooby.userProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentMomentBinding
import com.example.scooby.scooby.adapter.UserMomentAdapter
import com.example.scooby.scooby.viewModels.CommunityViewModel
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.SharedViewModel


class MomentFragment : Fragment() {
    private val args: MomentFragmentArgs by navArgs()
    private lateinit var communityViewModel: CommunityViewModel
    private var _binding: FragmentMomentBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMomentBinding.inflate(layoutInflater, container, false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
        //init()
        return binding.root
    }

    private fun init() {
        observable()
    }

    private fun observable() {
        communityViewModel.apply {
            Log.i("checkUserId","in Moment "+sharedViewModel.userId)
            getUserMoment(sharedViewModel.userId)
            userMomentResult.observe(viewLifecycleOwner) {
                    when (it) {
                        is BaseResponse.Loading -> showLoading()
                        is BaseResponse.Success -> {
                            stopLoading()
                            Log.i("InfoPost", it.data.toString())
                            binding.rvMoment.adapter = it.data?.let { it1 -> UserMomentAdapter(it1) }
                        }
                        is BaseResponse.Error -> {
                            stopLoading()
                            showToast("error in moment ")
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