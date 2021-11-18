package com.example.semester_project_crypto_wallet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.semester_project_crypto_wallet.databinding.ActivityMainBinding
import org.stellar.sdk.KeyPair

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.button1.setOnClickListener{
            val pair = KeyPair.random()
            editTextViews(pair)

        }
    }

    private fun editTextViews(pair: KeyPair) {
        binding.apply {
            publicKey.text = pair.accountId
            privateKey.text = String(pair.secretSeed)
        }
    }

    private fun editNickname(s: String) {
        binding.button1.text = s
    }
}