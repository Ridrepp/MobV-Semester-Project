package com.example.semester_project_crypto_wallet.ui.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.ui.User

class HomeViewModel : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
}

