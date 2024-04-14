package com.example.scooby.scooby.request

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.R
import com.example.scooby.databinding.FragmentDurationRequestBinding
import com.example.scooby.scooby.MainActivity


class DurationRequestFragment : Fragment() {
    private var binding: FragmentDurationRequestBinding? = null
    private val args: DurationRequestFragmentArgs by navArgs()
    private var flag = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDurationRequestBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
            nextBtn.setOnClickListener { onClickNext() }
            exitSection.setOnClickListener { findNavController().popBackStack() }
            optionEt.setOnClickListener {
                showPopup(it)
            }
        }
    }

    private fun onClickNext() {
        if (flag) {
            val listOfData = arrayOf(
                args.listOfData[0],
                args.listOfData[1],
                args.listOfData[2],
                binding?.optionEt?.text.toString()
            )
            val action =
                DurationRequestFragmentDirections.actionDurationRequestFragmentToLocationRequestFragment(
                    args.idPets,
                    listOfData
                )
            findNavController().navigate(action)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showPopup(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.duration_request_menu, popupMenu.menu)
        popupMenu.show()

        popupMenu.setOnMenuItemClickListener {
            when (it!!.itemId) {
                R.id.full_day_item -> {
                    binding?.optionEt?.text = "Full Day"
                }

                R.id.half_day_item -> {
                    binding?.optionEt?.text = "Half Day"
                }

                R.id.more_than_day_item -> {
                    binding?.optionEt?.text = "More than 1 Day"
                }

                else -> {
                    flag = false
                }
            }
            flag = true
            true
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
        binding = null
    }
}