package com.example.semester_project_crypto_wallet.ui.payments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver

class PaymentsViewModel (private val repository: Repository
) : ViewModel()
{
//    val publicKeyStr: LiveData<String>
//        get() = repository.getPublicKey()
//    //TODO: show unhashed key
//    val privateKeyStr: LiveData<String>
//        get() = repository.getPrivateKey()

    val receivers : LiveData<List<Receiver>>
        get() = repository.getReceivers()
    var publicKeyStr: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()


    fun confirmPin(){
        pin.value?.let {
            if(it.isNotEmpty()){
                //TODO: process pin
                Log.i("confirm_pin", it)
            }
        }
    }

}