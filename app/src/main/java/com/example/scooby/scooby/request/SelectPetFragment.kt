package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentSelectPetBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.adapter.MyPetsRequestAdapter
import com.example.scooby.scooby.viewModels.PetsViewModel
import com.example.scooby.utils.BaseResponse

/**
 * First screen to Request a service
 * Select user pets
 * author: Mohamed Ashraf
 * */
class SelectPetFragment : Fragment() {
    private var binding: FragmentSelectPetBinding? = null
    private lateinit var petsViewModel: PetsViewModel
    private val args: SelectPetFragmentArgs by navArgs()
    private lateinit var adapter: MyPetsRequestAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectPetBinding.inflate(inflater, container, false)
        petsViewModel = ViewModelProvider(this)[PetsViewModel::class.java]
        initView()
        return binding?.root
    }

    private fun initView() {
        observeMyPets()
        clickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            exitBtn.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
            nextBtn.text = "Next (\$${args.requestName[1]} /night)"
            addPetCard.setOnClickListener { findNavController().navigate(R.id.action_selectPetFragment_to_addPetsFragment) }
        }
    }


    private fun observeMyPets() {
        petsViewModel.apply {
            getMyPets()
            myPetsResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        adapter = MyPetsRequestAdapter(it.data!!, requireContext())
                        binding?.myPetsRv?.adapter = adapter
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                        showToast(it.msg)
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }
    }

    private fun onClickNext() {
        val listOfPetsData = adapter.getItemSelected().toTypedArray()
        if (listOfPetsData.isEmpty()) {
            Toast.makeText(requireContext(), "Not selected pet yet", Toast.LENGTH_LONG).show()
        } else {
            val action =
                SelectPetFragmentDirections.actionSelectPetFragmentToDateRequestFragment(
                    listOfPetsData,
                    args.requestName
                )
            findNavController().navigate(action)
        }
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.myPetsRv?.adapter = null
        binding = null
    }
}