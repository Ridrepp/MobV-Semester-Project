package com.example.semester_project_crypto_wallet.ui.transactions

import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
import android.util.Log
import androidx.lifecycle.*
import com.example.semester_project_crypto_wallet.data.Repository
import kotlinx.coroutines.launch


class TxViewModel (
    private val repository: Repository
    ) : ViewModel()
{
    val transactions: LiveData<List<Transaction>>
        get() = repository.getTransactions()


    val transactions_as_text: LiveData<String> = Transformations.map(transactions){
        var transactions_text = ""
        it?.forEach {
            transactions_text += it.toString() + ", "
        }
        transactions_text
    }

    fun showTransactions() {
        viewModelScope.launch { Log.i("getTransactions()", repository.getTransactions().toString()) }
    }

}