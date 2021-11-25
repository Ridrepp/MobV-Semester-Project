package com.example.semester_project_crypto_wallet.data.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.ui.contacts.ContactsViewModel
import com.example.semester_project_crypto_wallet.ui.payments.PaymentsViewModel
import com.example.semester_project_crypto_wallet.ui.register.RegisterViewModel
import com.example.semester_project_crypto_wallet.ui.transactions.TxViewModel

class ViewModelFactory (
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TxViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return TxViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return RegisterViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(PaymentsViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return PaymentsViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            @Suppress("unchecked_cast")
            return ContactsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}