package com.example.scooby.scooby.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.Constant
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentSelectPetBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.adapter.MyPetsRequestAdapter
import com.example.scooby.scooby.viewmodel.MyPetsViewModel


class SelectPetFragment : Fragment() {
    private var binding: FragmentSelectPetBinding? = null
    private val myPetsViewModel by viewModels<MyPetsViewModel>()
    private val args: SelectPetFragmentArgs by navArgs()
    private lateinit var userId: String
    private lateinit var adapter: MyPetsRequestAdapter
    private lateinit var listOfData: MutableList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectPetBinding.inflate(inflater, container, false)
        getUserId()
        initView()
        observeMyPets()
        return binding?.root
    }


    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            exitSection.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
        }
    }

    private fun observeMyPets() {
        myPetsViewModel.apply {
            getMyPets(userId)
            myPetsResult.observe(viewLifecycleOwner) { myPetsResponse ->
                stopLoading()
                myPetsResponse?.let {
                    adapter = MyPetsRequestAdapter(it,requireContext())
                    binding?.myPetsRv?.adapter = adapter
                    if (it.data.isEmpty()) {
                        Toast.makeText(
                            requireContext().applicationContext,
                            "Not have Pets",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun getUserId() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
    }


    private fun onClickNext() {
        listOfData = adapter.getItemSelected()
        if (listOfData.isEmpty()) {
            Toast.makeText(requireContext(), "Not selected pet yet", Toast.LENGTH_LONG).show()
        } else {
            val action =
                SelectPetFragmentDirections.actionSelectPetFragmentToDateRequestFragment(
                    listOfData.toTypedArray(),
                    args.requestName
                )
            findNavController().navigate(action)
        }
    }


    private fun stopLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            myPetsRv.visibility = View.VISIBLE
        }
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