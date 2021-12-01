package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet
import com.example.semester_project_crypto_wallet.ui.AES
import kotlinx.coroutines.launch
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Server

class SignInViewModel(private val repository: Repository) : ViewModel() {
    val privateKeyStrEditText: MutableLiveData<String> = MutableLiveData()
    val pinKeyEditText: MutableLiveData<String> = MutableLiveData()

    fun checkPrivateKey() : Boolean{
        return privateKeyStrEditText.value != null
    }

    fun checkPinKey() : Boolean{
        return pinKeyEditText.value != null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validatePublicKeyFromPrivateKey() : Boolean {
        val server = Server("https://horizon-testnet.stellar.org")

        return try {
            val myKeypair = KeyPair.fromSecretSeed(privateKeyStrEditText.value.toString())

            server.accounts().account(myKeypair.accountId.toString())
            KeyPair.fromSecretSeed(privateKeyStrEditText.value.toString()).canSign()

        }catch (e: Exception){
            Log.i("validatePKFromPKErr", e.toString())
            false
        }
    }

    fun validatePinKey() : Boolean{
        if (pinKeyEditText.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertUserToDb(){
        viewModelScope.launch {
            val encryptedSecretKey =
                pinKeyEditText.value?.let { AES.encrypt(privateKeyStrEditText.value.toString(), it) }.toString()

            try {
                repository.insertWallet(Wallet(KeyPair.fromSecretSeed(privateKeyStrEditText.value.toString()).accountId,encryptedSecretKey,0.toFloat()))
            } catch (e: Exception) {
                Log.i("insertUserToDBSignErr:", e.toString())
            }
        }
    }

}