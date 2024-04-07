package com.example.scooby.scooby.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scooby.R
import com.example.scooby.databinding.FragmentPawsBinding


class PawsFragment : Fragment() {
    private val adaptionFragment = AdaptionFragment()
    private val rescueFragment = RescueFragment()
    private val missingFragment = MissingFragment()
    private lateinit var binding : FragmentPawsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPawsBinding.inflate(inflater,container,false)
        initButtonCallBack()
        return binding.root
    }

    private fun initButtonCallBack() {
        binding.btnAdaption.setOnClickListener {
            replaceFragment(adaptionFragment)
        }
        binding.btnRescue.setOnClickListener {
            replaceFragment(rescueFragment)
        }
        binding.btnMissing.setOnClickListener {
            replaceFragment(missingFragment)
        }
    }

    private fun replaceFragment(fragment: Fragment){
         childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_paws,fragment)
            commit()
         }
    }

}