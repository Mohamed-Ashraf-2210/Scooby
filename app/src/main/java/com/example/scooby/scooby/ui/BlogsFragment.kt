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
    private val blogsViewModel by viewModels<BlogViewModel>()
    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!
    private lateinit var blogRV:RecyclerView
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogsBinding.inflate(inflater,container,false)
         mContext = requireContext()
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
        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                getBlogsData(it)
            }
        }
    }

    private fun getBlogsData(data:BlogResponse?) {
        blogRV = binding.RvBlogs
        blogRV.adapter = BlogAdapter(data!!,requireContext())
//        val adapter = BlogAdapter(data!!,requireContext())
//        binding.RvBlogs.adapter = adapter
    }

}