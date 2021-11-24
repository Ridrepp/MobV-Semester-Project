package com.example.semester_project_crypto_wallet.data.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.ui.generate_wallet.GenerateWalletViewModel
import com.example.semester_project_crypto_wallet.ui.transactions.TxViewModel

class ViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TxViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return TxViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(GenerateWalletViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return GenerateWalletViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}