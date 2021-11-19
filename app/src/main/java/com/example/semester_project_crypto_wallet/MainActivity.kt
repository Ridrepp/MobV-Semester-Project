package com.example.semester_project_crypto_wallet

import android.os.Bundle
import android.util.Log
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


        binding.createAccount.setOnClickListener{
            val pair = KeyPair.random()
            editTextViews(pair)
        }


        binding.button.setOnClickListener{
            val new_user = User()


        }

        binding.button2.setOnClickListener{

        }

    }




    private fun editTextViews(pair: KeyPair) {
        binding.apply {
            publicKey.text = pair.accountId
            privateKey.text = String(pair.secretSeed)

        }
    }

    private fun createAccount(user: User){
        user.createAccount()
    }







}