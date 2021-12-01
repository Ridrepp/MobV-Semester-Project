package com.example.semester_project_crypto_wallet.ui.logged_in

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.api.WebApi
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet
import kotlinx.coroutines.launch

class LoggedInViewModel(
    private val repository: Repository
) : ViewModel()
{
    val api: WebApi = WebApi()

    val balance:LiveData<Float>
        get() = repository.getBalance()

    val countCredentials: LiveData<Int>
        get() = repository.getCountCredentials()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    val wallet: LiveData<Wallet>
        get() = repository.getWallet()

    fun deleteWallet(){
        viewModelScope.launch{
            repository.deleteWallet()
        }
    }

    fun deleteTransactions(){
        viewModelScope.launch {
            repository.deleteTransactions()
        }
    }

    fun insertBalanceToDb(publicKey: String){
        viewModelScope.launch {
            try {
                val balance: Float = api.getXLMBalance(publicKey)
                repository.updateBalance(balance, publicKey)

            }catch (e: Exception){
                Log.i("insertBalanceToDBError:", e.toString())
            }
        }
    }

}
