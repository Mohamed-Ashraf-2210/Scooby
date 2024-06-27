package com.example.scooby.scooby.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.domain.request.AddResuestRequest
import com.example.scooby.databinding.FragmentConfirmNumberBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.viewModels.RequestViewModel
import com.example.scooby.utils.BaseResponse

/*
        args.idPets, (Array)
        args.requestName, (request name, request price)

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
    private lateinit var requestViewModel: RequestViewModel
    private val args: ConfirmNumberFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmNumberBinding.inflate(inflater, container, false)
        requestViewModel = ViewModelProvider(this)[RequestViewModel::class.java]
        showToast(args.listOfData.size.toString())
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            confirmBtn.setOnClickListener { sendDataRequest() }
        }
    }

    private fun sendDataRequest() {
        val phoneNum = binding?.phoneNumber?.text.toString()

        val totalPrice = args.requestName[1].toInt() * args.idPets.size
        val Location = listOf(args.listOfData[3], args.listOfData[4])
        var cardSecurityCode = ""
        var cardExpireDate = ""
        var cardNumber = ""
        var save = false
        if (args.listOfData[7] != "Cash") {
            cardNumber = args.listOfData[8]
            cardExpireDate = args.listOfData[9]
            cardSecurityCode = args.listOfData[10]
            save = args.listOfData[11] == "true"
        }
        val pick = args.listOfData[6] == "yes"
        val addResuestRequest = AddResuestRequest(
            serviceType = args.requestName[0],
            servicePrice = args.requestName[1].toInt(),
            requestTotalPrice = totalPrice,
            petsId = args.idPets.toList(),
            date = args.listOfData[0],
            time = args.listOfData[1],
            duration = args.listOfData[2],
            location = Location,
            notes = args.listOfData[5],
            pickUp = pick,
            payment = args.listOfData[7],
            saveCard = save,
            number = phoneNum,
            cardNumber = cardNumber,
            cardExpireDate = cardExpireDate,
            cardSecurityCode = cardSecurityCode
        )
        requestViewModel.apply {
            sendDataRequest(addResuestRequest)
            requestResult.observe(viewLifecycleOwner) {
                when (it) {
                    is BaseResponse.Loading -> {
                        showLoading()
                    }

                    is BaseResponse.Success -> {
                        stopLoading()
                        showToast("Send Success")
                    }

                    is BaseResponse.Error -> {
                        stopLoading()
                    }

                    else -> {
                        stopLoading()
                    }
                }
            }
        }

    }


    private fun stopLoading() {
        binding?.loading?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.loading?.visibility = View.VISIBLE
    }

    private fun showToast(msg: String?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
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