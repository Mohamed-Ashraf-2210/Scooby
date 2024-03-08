package com.example.scooby.scooby.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBlogsBinding
import com.example.scooby.scooby.adapter.BlogAdapter
import com.example.scooby.scooby.data.model.BlogResponse
import com.example.scooby.scooby.viewmodel.BlogViewModel
import com.example.scooby.utils.BaseResponse


class BlogsFragment : Fragment() {
    private val blogViewModel by viewModels<BlogViewModel>()
    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!
    private lateinit var blogRV:RecyclerView
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogsBinding.inflate(layoutInflater,container,false)
         mContext = requireContext()
         blogViewModel.blogResult.observe(viewLifecycleOwner){
             when (it) {
                 is BaseResponse.Loading -> {
                      showLoading()
                 }

                 is BaseResponse.Success -> {
                       stopLoading()
                     initRecycleView(it.data)
                 }

                 is BaseResponse.Error -> {
                    stopLoading()
//                    processError(it.msg)
                 }
                 else -> {
                    stopLoading()
                 }
             }
         }
         initGetBlog()

        return binding.root
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun initGetBlog() {
        blogViewModel.getBlogs()
    }

    private fun initRecycleView(data:BlogResponse?) {
        blogRV = binding.RvBlogs
        blogRV.adapter = BlogAdapter(data!!,requireContext())
//        val adapter = BlogAdapter(data!!,requireContext())
//        binding.RvBlogs.adapter = adapter
    }

}