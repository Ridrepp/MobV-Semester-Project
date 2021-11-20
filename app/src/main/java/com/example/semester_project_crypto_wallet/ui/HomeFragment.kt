package com.example.semester_project_crypto_wallet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
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

        binding.createAccount.setOnClickListener{
            val pair = KeyPair.random()
            editTextViews(pair)
        }


        binding.button.setOnClickListener{
            val new_user = User()
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
