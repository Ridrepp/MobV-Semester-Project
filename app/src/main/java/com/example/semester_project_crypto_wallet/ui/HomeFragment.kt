package com.example.semester_project_crypto_wallet.ui

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.databinding.FragmentHomeBinding
import com.example.semester_project_crypto_wallet.ui.viewModels.HomeViewModel
import org.stellar.sdk.KeyPair

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //return inflater.inflate(R.layout.fragment_home, container, false)

        // Changing policy because of response from Stellar server
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
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
        return binding.root
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
