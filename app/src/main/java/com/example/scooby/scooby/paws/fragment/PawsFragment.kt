package com.example.scooby.scooby.paws.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
        binding = FragmentPawsBinding.inflate(inflater, container, false)
        initButtonCallBack()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        childFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        addFragment(adaptionFragment)
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
             replace(R.id.fragment_container_paws,fragment.javaClass ,null)
             setReorderingAllowed(true)
             addToBackStack(null)
             commit()
         }
    }
    private fun addFragment(fragment: Fragment){
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_paws,fragment.javaClass ,null)
            setReorderingAllowed(true)
            addToBackStack("name")
            commit()
        }
    }

}