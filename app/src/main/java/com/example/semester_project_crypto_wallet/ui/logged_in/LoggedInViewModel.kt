package com.example.semester_project_crypto_wallet.ui.logged_in

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
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

    fun checkSession(): Boolean{
//        countCredentials.let {
//            Log.i("checkSession()", it.value.toString())
//            Log.i("checkSession()", "value = " + it.value)
//            Log.d("checkSession()", String.format("value = %d", it.value))
//        }
//        Log.i("checkSession()", countCredentials.value.toString())
//
//
//        Log.i("checkSession()", "publicKey = " + publicKey.value.toString())

        viewModelScope.launch {
//            countCredentials = repository.getCountCredentials()
//            publicKey = repository.getPublicKey()
            Log.i("checkSession()", countCredentials.value.toString())
            Log.i("checkSession()", publicKey.value.toString())

            Log.i("checkSession()", repository.getCountCredentials().value.toString())
            Log.i("checkSession()", repository.getPublicKey().value.toString())

            Log.i("checkSession()", "end")
        }

        return true
    }
}
