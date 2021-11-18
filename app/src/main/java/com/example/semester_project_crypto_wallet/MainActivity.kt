package com.example.semester_project_crypto_wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.semester_project_crypto_wallet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    var pair: KeyPair = KeyPair.random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.button1.setOnClickListener{
            editNickname("Button1")

        }
    }

    private fun editNickname(s: String) {
        binding.button1.text = s
    }
}