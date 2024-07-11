package com.example.scooby.scooby.blogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.R
import com.example.scooby.databinding.FragmentBlogsBinding
import com.example.scooby.scooby.adapter.BlogAdapter
import com.example.domain.blog.BlogResponse
import com.example.scooby.scooby.viewModels.BlogViewModel
import com.example.scooby.utils.BaseResponse
import com.google.android.material.bottomnavigation.BottomNavigationView


class BlogsFragment : Fragment() {
    private lateinit var blogsViewModel : BlogViewModel
    private var _binding: FragmentBlogsBinding? = null
    private val binding get() = _binding!!
    private lateinit var blogRV: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null){
            _binding = FragmentBlogsBinding.inflate(inflater, container, false)
            blogsViewModel = ViewModelProvider(this)[BlogViewModel::class.java]
            initGetBlog()
            backOffFragment()
        }

        return binding.root
    }
    private fun initGetBlog() {
        blogsViewModel.apply {
            getBlogs()
            blogResult.observe(viewLifecycleOwner) {
                when(it){
                    is BaseResponse.Loading -> showLoading()
                    is BaseResponse.Success -> {
                        stopLoading()
                        getBlogsData(it.data)
                    }
                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast()
                    }
                }
            }
        }
    }

    private fun getBlogsData(data: BlogResponse?) {
        blogRV = binding.RvBlogs
        blogRV.adapter = BlogAdapter(data!!,requireContext())
    }
    private fun backOffFragment() {
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun stopLoading() {
        binding.loading.visibility = View.GONE
        binding.RvBlogs.visibility = View.VISIBLE
    }
    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }
    private fun showToast() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        //(activity as AppCompatActivity).supportActionBar?.hide()
        val navBar: BottomNavigationView =
            (activity as AppCompatActivity).findViewById(R.id.bottomNavigationView)
        navBar.visibility = View.GONE
        getString(R.string.app_name)
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
}