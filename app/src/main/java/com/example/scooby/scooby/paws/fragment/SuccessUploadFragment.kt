package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scooby.R

/**
 * A simple [Fragment] subclass.
 * Use the [SuccessUploadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuccessUploadFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success_uplode, container, false)
    }


}