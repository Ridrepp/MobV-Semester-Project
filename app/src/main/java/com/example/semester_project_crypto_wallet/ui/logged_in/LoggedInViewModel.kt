package com.example.semester_project_crypto_wallet.ui.logged_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import kotlinx.coroutines.launch

class LoggedInViewModel(
    private val repository: Repository
) : ViewModel()
{
    private val _user: MutableLiveData<User> = MutableLiveData()

    val countCredentials: LiveData<Int>
        get() = repository.getCountCredentials()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    fun deleteUsers(){
        viewModelScope.launch{
            repository.clearCredentials()}
    }

}
