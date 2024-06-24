package com.example.scooby.scooby.request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentConfirmNumberBinding
import com.example.scooby.scooby.MainActivity

/*
        args.idPets,
        args.requestName,

        selectDateTv
        selectTime
        optionEt
        latitude
        longitude
        Notes
        pickUp
        paymentMethod
        cardNumber
        cardExpireDate
        cardSecurityCode
        saveCard
        Number Phone
*/

class ConfirmNumberFragment : Fragment() {

    private var binding: FragmentConfirmNumberBinding? = null
    private val args: ConfirmNumberFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmNumberBinding.inflate(inflater,container,false)

        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            confirmBtn.setOnClickListener { goToConfirm() }
        }
    }

    private fun goToConfirm() {
        val phoneNum = binding?.phoneNumber?.text.toString()
        val listOfData = arrayOf(
            args.listOfData[0],
            args.listOfData[1],
            args.listOfData[2],
            args.listOfData[3],
            args.listOfData[4],
            args.listOfData[5],
            args.listOfData[6],
            args.listOfData[7],
            args.listOfData[8],
            args.listOfData[9],
            args.listOfData[10],
            args.listOfData[11],
            phoneNum
        )

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).hideBottomNavigationView()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showBottomNavigationView()
    }
}