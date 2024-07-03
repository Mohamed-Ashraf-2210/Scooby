package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        binding?.apply {

            cardNumberLayout.addTextChangedListener(object : TextWatcher {
                private val space = ' '

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    // Remove the TextWatcher to prevent infinite loop
                    cardNumberLayout.removeTextChangedListener(this)

                    // Remove spaces
                    val text = s.toString().replace(" ", "")
                    val formattedText = StringBuilder()

                    // Add spaces every 4 characters
                    for (i in text.indices) {
                        if (i > 0 && i % 4 == 0) {
                            formattedText.append(space)
                        }
                        formattedText.append(text[i])
                    }

                    // Set the formatted text and move the cursor to the end
                    cardNumberLayout.setText(formattedText.toString())
                    cardNumberLayout.setSelection(formattedText.length)

                    // Re-attach the TextWatcher
                    cardNumberLayout.addTextChangedListener(this)
                }
            })

            expiryDateLayout.addTextChangedListener(object : TextWatcher {
                private val slash = '/'

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    // Remove the TextWatcher to prevent infinite loop
                    expiryDateLayout.removeTextChangedListener(this)

                    // Remove slashes
                    val text = s.toString().replace("/", "")
                    val formattedText = StringBuilder()

                    // Add slash after MM
                    for (i in text.indices) {
                        if (i == 2) {
                            formattedText.append(slash)
                        }
                        formattedText.append(text[i])
                    }

                    // Set the formatted text and move the cursor to the end
                    expiryDateLayout.setText(formattedText.toString())
                    expiryDateLayout.setSelection(formattedText.length)

                    // Re-attach the TextWatcher
                    expiryDateLayout.addTextChangedListener(this)
                }
            })
        }
    }

    private fun goToConfirm() {
        binding?.apply {
            val cardNumber = cardNumberLayout.text.toString()
            val expiryDate = expiryDateLayout.text.toString()
            val securityCode = securityCodeLayout.text.toString()
            if (cardNumber.length == 19 && securityCode.length == 3) {
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