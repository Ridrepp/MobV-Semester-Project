package com.example.semester_project_crypto_wallet.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: Repository
    ) : ViewModel()
    {
//    val publicKeyStr: LiveData<String>
//        get() = repository.getPublicKey()
//    //TODO: show unhashed key
//    val privateKeyStr: LiveData<String>
//        get() = repository.getPrivateKey()

        var publicKeyStr: MutableLiveData<String> = MutableLiveData()
        var privateKeyStr: MutableLiveData<String> = MutableLiveData()
        val pin: MutableLiveData<String> = MutableLiveData()
        private val user = User()

        fun confirmPin(){
            pin.value?.let {
                if(it.isNotEmpty()){
                    //TODO: process pin
                    Log.i("confirm_pin", it)
                }
            }
        }

        fun generateKeyPair(){
            //TODO: generate keypair
            user.generateKeys()

            // LIVE DATA FOR USER INTERFACE
            publicKeyStr.value = user.my_keypair.accountId
            privateKeyStr.value = String(user.my_keypair.secretSeed)

//            user.createAccount()
//            Log.i("generateKeyPair()", user.my_keypair.accountId)
//            Log.i("generateKeyPair()", String(user.my_keypair.secretSeed))




        }

        fun createAccount(){
            user.createAccount()
        }

        fun insertUserToDb(){
            //TODO: insert into room by repository
            viewModelScope.launch {
                repository.insertKeyPair(Credentials(user.my_keypair.accountId,String(user.my_keypair.secretSeed)))
            }

        }
}