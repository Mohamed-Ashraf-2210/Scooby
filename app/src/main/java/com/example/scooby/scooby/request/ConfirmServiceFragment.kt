package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentConfirmServiceBinding
import com.example.scooby.scooby.MainActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
*/

class ConfirmServiceFragment : Fragment() {
    private var binding: FragmentConfirmServiceBinding? = null
    private val args: ConfirmServiceFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmServiceBinding.inflate(inflater, container, false)
        init()
        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.P)
    private fun init() {
        binding?.apply {
            val total = args.requestName[1].toInt() * args.idPets.size
            nightPrice.text = "$${args.requestName[1]}"
            petsNumberTv.text = "For ${args.idPets.size} pet"
            totalPrice.text = "$$total for a night"
            dataRequest.text = getFormattedTodayDate()
            normalPriceValue.text = "Normal price is $${args.requestName[1]} / night"
            confirmBtn.setOnClickListener { goToConfirm() }
        }
    }

    private fun goToConfirm() {
        binding?.apply {
            val cardNumber = cardNumberLayout.text.toString()
            val expiryDate = expiryDateLayout.text.toString()
            val securityCode = securityCodeLayout.text.toString()
            if (cardNumber.length == 16 && securityCode.length == 3) {
                val flag = saveCard.isChecked
                val listOfData = arrayOf(
                    args.listOfData[0],
                    args.listOfData[1],
                    args.listOfData[2],
                    args.listOfData[3],
                    args.listOfData[4],
                    args.listOfData[5],
                    args.listOfData[6],
                    args.listOfData[7],
                    cardNumber,
                    expiryDate,
                    securityCode,
                    flag.toString()
                )
                findNavController().navigate(
                    ConfirmServiceFragmentDirections.actionConfirmServiceFragmentToConfirmNumberFragment(
                        args.idPets,
                        args.requestName,
                        listOfData
                    )
                )
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTodayDate(): String {
        val formatter = DateTimeFormatter.ofPattern("d'st' MMMM, yyyy")
        val today = LocalDate.now()
        return today.format(formatter)
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