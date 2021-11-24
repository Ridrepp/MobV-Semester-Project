package com.example.semester_project_crypto_wallet.ui.pin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel: ViewModel() {
    val pin: MutableLiveData<String> = MutableLiveData()

//    val pin: LiveData<String>
//        get() = _pin

    fun confirmPin(){
        pin.value?.let {
            if(it.isNotEmpty()){
                //TODO: process pin
                Log.i("confirm_pin", it)
            }
        }
    }
}