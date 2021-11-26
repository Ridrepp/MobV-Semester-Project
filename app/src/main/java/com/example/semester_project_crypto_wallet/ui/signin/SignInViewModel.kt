package com.example.semester_project_crypto_wallet.ui.signin

import android.net.wifi.hotspot2.pps.Credential
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver

class SignInViewModel(private val repository: Repository) : ViewModel() {
    lateinit var credentialsPublicKeys: String
    val publicKeyStrEditText: MutableLiveData<String> = MutableLiveData()

    val findkeycredential: LiveData<Credentials>
        get() = repository.findKeyCredentials(publicKeyStrEditText.value.toString())

//    val transactions_as_text: LiveData<String> = Transformations.map(transactions){
//        var transactions_text = ""
//        it?.forEach {
//            transactions_text += it.toString() + ", "
//        }
//        transactions_text
//    }

//    fun showTransactions() {
//        viewModelScope.launch { Log.i("getTransactions()", repository.getTransactions().toString()) }
//    }

    fun logInAccount() : Boolean{
        if (publicKeyStrEditText.value =="" || publicKeyStrEditText.value == null)
            return false
//            if (publicKeyStrEditText.value?.let { repository.getPublicKeysFromCredentials(it) } != true)
//            {
//                return false
//            }


        publicKeyStrEditText.value?.let { Log.i("publicKeyFromEditText", it) }
        return true
    }
}