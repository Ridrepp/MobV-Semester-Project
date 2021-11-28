package com.example.semester_project_crypto_wallet.ui.logged_in

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.User
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Balance
import kotlinx.coroutines.launch
import org.stellar.sdk.Server

class LoggedInViewModel(
    private val repository: Repository
) : ViewModel()
{
    private val _user: MutableLiveData<User> = MutableLiveData()

    val balance:LiveData<Float>
        get() = repository.getBalance()

    val countCredentials: LiveData<Int>
        get() = repository.getCountCredentials()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    fun deleteUsers(){
        viewModelScope.launch{
            repository.clearCredentials()}
    }

    fun getXLMbalance(publicKey: String): Float {
        val server = Server("https://horizon-testnet.stellar.org")
        Log.i("IS THIS IT?", publicKey)
        val account = server.accounts().account(publicKey)

        return account.balances[0].balance.toFloat()
    }

    fun insertBalanceToDb(publicKey: String){
        viewModelScope.launch {
            try {
                repository.insertBalance(Balance(getXLMbalance(publicKey)))
                Log.i("***", "*****BALANCE SAVED SUCCESSFULLY******")
            }catch (e: Exception){
                Log.i("EXCEPTION", e.toString())
            }

        }

    }

}
