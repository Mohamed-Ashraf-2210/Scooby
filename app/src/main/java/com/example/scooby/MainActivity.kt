package com.example.scooby

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.scooby.databinding.ActivityMainBinding
import com.example.scooby.scooby.adapter.MyPetsSideMenuAdapter
import com.example.scooby.scooby.data.model.MyPetsResponse
import com.example.scooby.scooby.viewmodel.MyPetsViewModel
import com.example.scooby.utils.BaseResponse
import com.example.scooby.utils.Constant
import com.example.scooby.utils.TokenManager


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myPetsViewModel by viewModels<MyPetsViewModel>()
    private lateinit var myPetsRV: RecyclerView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Scooby)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Test"
//        binding.bottomNavigationView.setupWithNavController(navController)

//        var name = findViewById<TextView>(R.id.user_name).text.toString()
//        name = TokenManager.getAuth(this, Constant.USER_NAME).toString()

        myPetsViewModel.myPetsResult.observe(this, Observer {
            when (it) {
                is BaseResponse.Loading -> {}
                is BaseResponse.Success -> {
                    getMyPetsData(it.data)
                }

                is BaseResponse.Error -> {}
                else -> {}
            }
        })
        myPetsViewModel.getMyPets()

    }

    private fun getMyPetsData(data: MyPetsResponse?) {
        myPetsRV = binding.drawerLayout.findViewById(R.id.your_pets_rv)
        myPetsRV.adapter = MyPetsSideMenuAdapter(data!!, this)
    }
}