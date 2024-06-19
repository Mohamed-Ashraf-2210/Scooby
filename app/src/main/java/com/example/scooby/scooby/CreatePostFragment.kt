package com.example.scooby.scooby

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scooby.R
import com.example.scooby.databinding.FragmentCreatePostBinding

class CreatePostFragment : Fragment() {
    private var binding : FragmentCreatePostBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostBinding.inflate(inflater,container,false)
        return binding!!.root
    }

}