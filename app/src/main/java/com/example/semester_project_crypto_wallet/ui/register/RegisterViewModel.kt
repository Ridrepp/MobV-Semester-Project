package com.example.semester_project_crypto_wallet.ui.register

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.api.WebApi
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet
import com.example.semester_project_crypto_wallet.ui.AES
import kotlinx.coroutines.launch
import org.stellar.sdk.KeyPair

class RegisterViewModel(
    private val repository: Repository
) : ViewModel() {

    private val api:WebApi = WebApi()

    lateinit var myKeypair : KeyPair
    var publicKeyStr: MutableLiveData<String> = MutableLiveData()
    var privateKeyStr: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()

    fun generateKeyPair() {
        myKeypair = api.generateKeys()
        publicKeyStr.value = myKeypair.accountId
        privateKeyStr.value = String(myKeypair.secretSeed)
    }

    fun validatePin(): Boolean{
        Log.i("validatePin", "" + pin.value?.length)
        if (pin.value?.length == 4) return true
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertUserToDb(){

        api.createAccount(myKeypair)

        viewModelScope.launch {
            val encryptedSecretKey = pin.value?.let { AES.encrypt(String(myKeypair.secretSeed), it) }.toString()

            try {
                repository.insertWallet(Wallet(myKeypair.accountId,encryptedSecretKey,0.toFloat()))
                Log.i("***", "*****REGISTRATION SUCCESSFUL******")
            } catch (e: Exception) {
                Log.i("EXCEPTION", e.toString())
            }
        }
    }
}