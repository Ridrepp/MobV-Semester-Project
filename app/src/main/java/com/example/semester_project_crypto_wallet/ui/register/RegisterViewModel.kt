package com.example.semester_project_crypto_wallet.ui.register

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.api.WebApi
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.ui.AES
import kotlinx.coroutines.launch
import org.stellar.sdk.KeyPair

class RegisterViewModel(
    private val repository: Repository
) : ViewModel() {

    val api:WebApi = WebApi()

    lateinit var my_keypair : KeyPair
    var publicKeyStr: MutableLiveData<String> = MutableLiveData()
    var privateKeyStr: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()

    fun generateKeyPair() {
        my_keypair = api.generateKeys()
        publicKeyStr.value = my_keypair.accountId
        privateKeyStr.value = String(my_keypair.secretSeed)
    }

    fun validatePin(): Boolean{
        Log.i("validatePin", "" + pin.value?.length)
        if (pin.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertUserToDb(){

        api.createAccount(my_keypair)

        viewModelScope.launch {
            val encrypted_secretkey = pin.value?.let { AES.encrypt(String(my_keypair.secretSeed), it) }.toString()

            try {
                repository.insertKeyPair(Credentials(my_keypair.accountId,encrypted_secretkey))
                Log.i("***", "*****REGISTRATION SUCCESSFUL******")
            } catch (e: Exception) {
                Log.i("EXCEPTION", e.toString())
            }
        }
    }
}