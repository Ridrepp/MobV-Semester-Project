package com.example.semester_project_crypto_wallet.ui.signin_wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentSigninWalletBinding

class SignInWalletFragment: Fragment() {
    private val signInWalletViewModel: SignInWalletViewModel by viewModels()
    private lateinit var binding: FragmentSigninWalletBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signin_wallet, container, false
        )
        binding.signInWalletModel = signInWalletViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}