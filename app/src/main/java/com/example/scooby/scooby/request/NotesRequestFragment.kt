package com.example.scooby.scooby.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.scooby.databinding.FragmentNotesRequestBinding
import com.example.scooby.scooby.MainActivity


class NotesRequestFragment : Fragment() {
    private var binding: FragmentNotesRequestBinding? = null
    private val args: NotesRequestFragmentArgs by navArgs()
    private var pickUp = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesRequestBinding.inflate(inflater, container, false)
        initView()
        return binding?.root
    }

    private fun initView() {
        binding?.apply {
            backScreen.setOnClickListener { findNavController().popBackStack() }
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

    private fun onClickNext() {
        binding?.apply {
            if (pickUp.isEmpty()) {
                Toast.makeText(requireContext(), "Not selected Pick up yet", Toast.LENGTH_LONG)
                    .show()
            } else {
                val listOfData = arrayOf(
                    args.listOfData[0],
                    args.listOfData[1],
                    args.listOfData[2],
                    args.listOfData[3],
                    notesEt.text.toString(),
                    pickUp
                )
                val action =
                    NotesRequestFragmentDirections.actionNotesRequestFragmentToSummaryRequestFragment(
                        args.idPets,
                        args.requestName,
                        listOfData
                    )
                findNavController().navigate(action)
            }
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