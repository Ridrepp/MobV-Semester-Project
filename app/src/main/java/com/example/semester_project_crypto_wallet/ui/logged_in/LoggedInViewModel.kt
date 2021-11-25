package com.example.semester_project_crypto_wallet.ui.logged_in

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.User

class LoggedInViewModel : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
}
