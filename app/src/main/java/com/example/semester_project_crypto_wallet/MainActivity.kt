package com.example.semester_project_crypto_wallet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.semester_project_crypto_wallet.databinding.ActivityMainBinding
import org.stellar.sdk.KeyPair
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // Changing policy because of response from Stellar server
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)


        binding.createAccount.setOnClickListener{
            val pair = KeyPair.random()
            editTextViews(pair)
        }


        binding.button.setOnClickListener{
            // First user
            val first_user = User()
            first_user.createAccount()
            first_user.checkBalances()

            //Second user
            val second_user = User()
            second_user.createAccount()
            second_user.checkBalances()

            Log.i("****************","********TRANSACTION*************")
            first_user.sendPayment(second_user.my_keypair)

            Log.i("****************","********BALANCES AGAIN*************")
            first_user.checkBalances()
            second_user.checkBalances()

            Log.i("****************","********END*************")



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