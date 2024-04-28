package com.example.scooby.scooby.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentPaymentMethodsBinding


class PaymentMethodsFragment : Fragment() {
    private var binding: FragmentPaymentMethodsBinding? = null
    private val args: PaymentMethodsFragmentArgs by navArgs()
    private var paymentMethod: String = "Visa"

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentMethodsBinding.inflate(inflater, container, false)
        initView()

        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.visa_btn -> {
                        paymentMethod = "Visa"
                    }

                    R.id.masterCard_btn -> {
                        paymentMethod = "Master Card"
                    }

                    R.id.localCards_btn -> {
                        paymentMethod = "Local Cards"
                    }

                    R.id.mobileWallet_btn -> {
                        paymentMethod = "Mobile Wallet"
                    }

                    R.id.cash_btn -> {
                        paymentMethod = "Cash"
                    }
                }
            }
            nextBtn.setOnClickListener {
                val listOfData = arrayOf(
                    args.listOfData[0],
                    args.listOfData[1],
                    args.listOfData[2],
                    args.listOfData[3],
                    args.listOfData[4],
                    args.listOfData[5],
                    args.listOfData[6],
                    paymentMethod
                )

                findNavController().navigate(
                    PaymentMethodsFragmentDirections.actionPaymentMethodsFragmentToConfirmServiceFragment(
                        args.idPets,
                        args.requestName,
                        listOfData
                    )
                )
            }
        }
    }

}