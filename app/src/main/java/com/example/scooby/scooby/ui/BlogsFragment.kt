package com.example.scooby.scooby.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBlogsBinding
import com.example.scooby.scooby.adapter.BlogAdapter
import com.example.domain.blog.BlogResponse
import com.example.scooby.scooby.viewmodel.BlogViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class BlogsFragment : Fragment() {
    private val blogsViewModel by viewModels<BlogViewModel>()
    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!
    private lateinit var blogRV: RecyclerView
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlogsBinding.inflate(inflater, container, false)
        mContext = requireContext()
        initGetBlog()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //(activity as AppCompatActivity).supportActionBar?.hide()
        val navBar: BottomNavigationView =
            (activity as AppCompatActivity).findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
        getString(R.string.app_name)

        val appBar: Toolbar = (activity as AppCompatActivity).findViewById(R.id.tool_bar)
        appBar.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
        val navBar: BottomNavigationView =
            (activity as AppCompatActivity).findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.VISIBLE

//        val appBar : AppBarLayout = (activity as AppCompatActivity).findViewById(R.id.tool_bar)
//        appBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.loading.visibility = View.GONE
        binding.RvBlogs.visibility = View.VISIBLE
    }

    private fun initGetBlog() {
        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                stopLoading()
                getBlogsData(it)
            }
        }
    }

    private fun getBlogsData(data: BlogResponse?) {
        blogRV = binding.RvBlogs
        blogRV.adapter = BlogAdapter(data!!, requireContext())
    }

}