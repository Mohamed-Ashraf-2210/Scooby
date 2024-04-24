package com.example.scooby.scooby.request

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentPaymentMethodsBinding
import com.example.scooby.databinding.FragmentSummaryRequestBinding
import com.example.scooby.scooby.viewmodel.MyPetsViewModel
import java.util.Calendar


class PaymentMethodsFragment : Fragment() {
    private var binding: FragmentPaymentMethodsBinding? = null
    private val args: PaymentMethodsFragmentArgs by navArgs()

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
            nextBtn.setOnClickListener {
                val listOfData = arrayOf(
                    args.listOfData[0],
                    args.listOfData[1],
                    args.listOfData[2],
                    args.listOfData[3],
                    args.listOfData[4],
                    args.listOfData[5],
                    args.listOfData[6],
                    radioGroup.checkedRadioButtonId.toString()
                    )
                Toast.makeText(requireActivity(), radioGroup.checkedRadioButtonId.toString(), Toast.LENGTH_SHORT).show()

                findNavController().navigate(
                    PaymentMethodsFragmentDirections.actionPaymentMethodsFragmentToConfirmServiceFragment(
                        args.idPets,
                        args.requestName,
                        listOfData
                    )
                )}
        }
    }

}