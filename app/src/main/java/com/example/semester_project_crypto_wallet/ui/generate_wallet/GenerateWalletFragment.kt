package com.example.semester_project_crypto_wallet.ui.generate_wallet

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentGenerateWalletBinding

class GenerateWalletFragment : Fragment(){
    private val generateWalletViewModel: GenerateWalletViewModel by viewModels()
    private lateinit var binding: FragmentGenerateWalletBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_generate_wallet, container, false
        )
        binding.generateWalletModel = generateWalletViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}