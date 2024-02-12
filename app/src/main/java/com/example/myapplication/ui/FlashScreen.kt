package com.example.myapplication.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FlashscreenBinding

class FlashScreen: AppCompatActivity() {
    private lateinit var binding:FlashscreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FlashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
        }

        binding.startbtn.setOnClickListener {
            MovingActivity()
        }


    }

    private fun MovingActivity() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }
}