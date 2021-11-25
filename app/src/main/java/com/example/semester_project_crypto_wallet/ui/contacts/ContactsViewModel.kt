package com.example.semester_project_crypto_wallet.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import kotlinx.coroutines.launch

class ContactsViewModel (private val repository: Repository) : ViewModel(){
    var publicKeyStr: String = String()
    var contactName: String = String()
    fun insertContactToDatabase(){
        viewModelScope.launch {
            repository.insertReceiver(Receiver(publicKeyStr,contactName))
        }
    }
}