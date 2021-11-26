package com.example.semester_project_crypto_wallet.ui.register

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.ui.AES
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: Repository
) : ViewModel() {
//    val publicKeyStr: LiveData<String>
//        get() = repository.getPublicKey()
//    //TODO: show unhashed key
//    val privateKeyStr: LiveData<String>
//        get() = repository.getPrivateKey()

    var publicKeyStr: MutableLiveData<String> = MutableLiveData()
    var privateKeyStr: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()
    private val user = User()

//        fun confirmPin(): String {
//            pin.value?.let {
//                if(it.isNotEmpty()){
//                    return it
//                }
//            }
//        }

    fun generateKeyPair() {
        //TODO: generate keypair
        user.generateKeys()

        // LIVE DATA FOR USER INTERFACE
        publicKeyStr.value = user.my_keypair.accountId
        privateKeyStr.value = String(user.my_keypair.secretSeed)


    }

    fun createAccount() {
        user.createAccount()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun insertUserToDb() {
        viewModelScope.launch {

            // ENCRYPT SECRET KEY WITH PIN FROM USER
            val encrypted_secretkey =
                pin.value?.let { AES.encrypt(String(user.my_keypair.secretSeed), it) }.toString()

            // INSERT PUBLIC KEY AND ENCRYPTED SECRET KEY TO DB
            repository.insertKeyPair(Credentials(user.my_keypair.accountId, encrypted_secretkey))


        }

    }
}