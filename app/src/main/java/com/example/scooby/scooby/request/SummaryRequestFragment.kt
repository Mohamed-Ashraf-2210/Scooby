package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.Constant
import com.example.scooby.R
import com.example.scooby.TokenManager
import com.example.scooby.databinding.FragmentSummaryRequestBinding
import com.example.scooby.scooby.MainActivity
import com.example.scooby.scooby.adapter.PetsSummaryRequestAdapter
import com.example.scooby.scooby.viewmodel.MyPetsViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class SummaryRequestFragment : Fragment() {
    private var binding: FragmentSummaryRequestBinding? = null
    private val myPetsViewModel by viewModels<MyPetsViewModel>()
    private val args: SummaryRequestFragmentArgs by navArgs()
    private lateinit var userId: String
    private lateinit var adapter: PetsSummaryRequestAdapter
    private val calendar = Calendar.getInstance()
    private var pickUp = ""

    /*
        args.idPets,
        args.requestName,

        selectDateTv
        selectTime
        optionEt
        latitude
        longitude
        location
        Notes
        pickUp
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryRequestBinding.inflate(inflater, container, false)
        getUserId()
        pickUp = args.listOfData[7]
        binding?.apply {
            selectDate.text = args.listOfData[0]
            selectTime.text = args.listOfData[1]
            optionServiceTv.text = args.listOfData[2]
            yourLocationTv.text = args.listOfData[5]
            yourNotesEt.setText(args.listOfData[6])
            yesChecked.visibility = if (pickUp == "yes") View.VISIBLE else View.GONE
            noChecked.visibility = if (pickUp == "no") View.VISIBLE else View.GONE
            nextBtn.text = "Next (\$${args.requestName[1]} /night)"
        }
        initView()
        observeMyPets()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            selectDate.setOnClickListener { showDatePickerSelectDate() }
            selectTime.setOnClickListener { showTimePickerSelectDate() }
            optionServiceTv.setOnClickListener { showPopup(it) }
            nextBtn.setOnClickListener {
                onClickNext()
            }
            yesCard.setOnClickListener {
                onClickYesCard()
            }
            noCard.setOnClickListener {
                onClickNoCard()
            }
        }
    }

    private fun onClickNext() {
        binding?.apply {

            val listOfData = arrayOf(
                selectDate.text.toString(),
                selectTime.text.toString(),
                optionServiceTv.text.toString(),
                args.listOfData[3],
                args.listOfData[4],
                args.listOfData[5],
                yourNotesEt.text.toString(),
                pickUp
            )
            val action =
                SummaryRequestFragmentDirections.actionSummaryRequestFragmentToConfirmNumberFragment(
                    args.idPets,
                    args.requestName,
                    listOfData
                )
            findNavController().navigate(action)
        }
    }

    private fun getUserId() {
        userId = TokenManager.getAuth(requireContext(), Constant.USER_ID).toString()
    }

    private fun observeMyPets() {
        myPetsViewModel.apply {
            getMyPets(userId)
            myPetsResult.observe(viewLifecycleOwner) {
                adapter = PetsSummaryRequestAdapter(it!!, requireContext(), args.idPets)
                binding?.petsRv?.adapter = adapter
            }
        }
    }

    // region Pick date
    private fun showDatePickerSelectDate() {
        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding?.selectDate?.text = formattedDate.toString()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    // endregion
    // region Pick time
    @SuppressLint("SimpleDateFormat")
    private fun showTimePickerSelectDate() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            binding?.selectTime?.text = SimpleDateFormat("HH:mm").format(calendar.time).toString()
        }
        TimePickerDialog(
            requireContext(),
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }

    // endregion
    // region Popup menu
    @SuppressLint("SetTextI18n")
    private fun showPopup(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.duration_request_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it!!.itemId) {
                R.id.full_day_item -> {
                    binding?.optionServiceTv?.text = "Full Day"
                }

                R.id.half_day_item -> {
                    binding?.optionServiceTv?.text = "Half Day"
                }

                R.id.more_than_day_item -> {
                    binding?.optionServiceTv?.text = "More than 1 Day"
                }
            }
            true
        }
    }

    // endregion

    private fun onClickNoCard() {
        binding?.apply {
            pickUp = "no"
            yesChecked.visibility = View.GONE
            noChecked.visibility = View.VISIBLE
        }
    }

    private fun onClickYesCard() {
        binding?.apply {
            pickUp = "yes"
            yesChecked.visibility = View.VISIBLE
            noChecked.visibility = View.GONE
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
        binding?.petsRv?.adapter = null
        binding = null
    }

}