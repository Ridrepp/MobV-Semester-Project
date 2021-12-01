package com.example.semester_project_crypto_wallet.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import kotlinx.coroutines.launch

class ContactsViewModel (private val repository: Repository) : ViewModel(){
    var publicKeyStr: String = String()
    var contactName: String = String()

    val receivers: LiveData<List<Receiver>>
        get() = repository.getReceivers()

    fun insertContactToDatabase(): Int{
        if (publicKeyStr == ""  && contactName == ""){
            return 1
        }
        if (publicKeyStr == ""){
            return 2
        }
        if (contactName == ""){
            return 3
        }
        viewModelScope.launch {
            repository.insertReceiver(Receiver(publicKeyStr,contactName))
        }
        return 0
    }

    fun deleteContactFromDB(name: String, address: String){
        viewModelScope.launch {
            repository.deleteContact(name, address)
        }
    }
}