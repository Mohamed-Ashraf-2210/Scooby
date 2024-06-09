package com.example.scooby.scooby.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.data.Constant
import com.example.scooby.R
import com.example.data.local.TokenManager
import com.example.scooby.databinding.FragmentCommunityBinding
import com.example.scooby.scooby.adapter.MyMomentPostsAdapter
import com.example.scooby.scooby.adapter.PublicPostsAdapter
import com.example.scooby.scooby.viewmodel.CommunityViewModel


class CommunityFragment : Fragment() {
    private var binding: FragmentCommunityBinding? = null
    private lateinit var communityViewModel: CommunityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        communityViewModel = ViewModelProvider(this)[CommunityViewModel::class.java]
        initView()


        return binding?.root
    }

    private fun initView() {
        switchButton()
        observePublicPosts()
    }

    private fun switchButton() {
        binding?.apply {
            switchOnOff.setOnCheckedChangeListener { _, checked ->
                when {
                    checked -> {
                        publicBtn.apply {
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
                        }
                        myMomentsBtn.apply {
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        }
                        observeMyMomentPosts()
                        postRv.visibility = View.GONE
                        myMomentPostRv.visibility = View.VISIBLE
                    }

                    else -> {
                        publicBtn.apply {
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        }
                        myMomentsBtn.apply {
                            setTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
                        }
                        observePublicPosts()
                        postRv.visibility = View.VISIBLE
                        myMomentPostRv.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun observePublicPosts() {
        communityViewModel.apply {
            getPublicPosts()
            publicPostsResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding?.postRv?.adapter =
                        PublicPostsAdapter(this, it, requireContext())
                }
            }
        }
    }

    private fun observeMyMomentPosts() {
        communityViewModel.apply {
            getMyMomentPosts()
            myMomentPostsResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    binding?.myMomentPostRv?.adapter =
                        MyMomentPostsAdapter(this, it, requireContext())
                }
            }
        }
    }
}