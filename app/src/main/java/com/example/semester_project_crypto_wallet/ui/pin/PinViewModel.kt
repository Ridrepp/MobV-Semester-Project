package com.example.semester_project_crypto_wallet.ui.pin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel: ViewModel() {
    private val _pin: MutableLiveData<String> = MutableLiveData()

    val pin: LiveData<String>
        get() = _pin

}