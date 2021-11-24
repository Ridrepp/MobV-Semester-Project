package com.example.semester_project_crypto_wallet.ui.generate_wallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentGenerateWalletBinding
import com.example.semester_project_crypto_wallet.ui.transactions.TxViewModel
import kotlinx.coroutines.launch

class GenerateWalletFragment : Fragment(){

    private lateinit var generateWalletViewModel: GenerateWalletViewModel
    private lateinit var binding: FragmentGenerateWalletBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        generateWalletViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(GenerateWalletViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_generate_wallet,
            container,
            false
        )

        binding.generateWalletModel = generateWalletViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.confirmGeneration.setOnClickListener{
            lifecycleScope.launch {
                generateWalletViewModel.confirmPin()
            }
        }

        lifecycleScope.launch {
            generateWalletViewModel.generateKeyPair()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.button.setOnClickListener {it.findNavController().navigate(R.id.action_generateWalletFragment_to_pinFragment)}
    }
}