package com.example.scooby

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.scooby.databinding.FragmentResetPasswordBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ResetPassword : Fragment() {
   private lateinit var binding:FragmentResetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        initNavigation()
        return binding.root
    }

    private fun initNavigation() {
        binding.btnContinue.setOnClickListener {v->
            Navigation.findNavController(v).navigate(R.id.action_resetPassword_to_createNewPassword)
        }
    }


}