package com.example.semester_project_crypto_wallet.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials

class SignInViewModel(private val repository: Repository) : ViewModel() {
    val publicKeyStrEditText: MutableLiveData<String> = MutableLiveData()
    val privateKeyStrEditText: MutableLiveData<String> = MutableLiveData()
    val pinKeyEditText: MutableLiveData<String> = MutableLiveData()

    lateinit var findKeyCredential: LiveData<Credentials>

    fun logInAccount() : Boolean{
        if (publicKeyStrEditText.value =="" || publicKeyStrEditText.value == null)
            return false
        findKeyCredential = repository.findKeyCredentials(publicKeyStrEditText.value.toString())
        return true
    }
}