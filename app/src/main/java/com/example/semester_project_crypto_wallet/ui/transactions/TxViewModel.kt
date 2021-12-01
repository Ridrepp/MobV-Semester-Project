package com.example.semester_project_crypto_wallet.ui.transactions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.api.WebApi
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
import kotlinx.coroutines.launch
import org.stellar.sdk.responses.operations.PaymentOperationResponse

class TxViewModel (
    private val repository: Repository
    ) : ViewModel()
{
    private val api: WebApi = WebApi()

    val transactions: LiveData<List<Transaction>>
        get() = repository.getTransactions()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    fun addPaymentsToDB(publicKey: String){
        viewModelScope.launch {
            try {
                val response = api.getTransactionsFromServer(publicKey)

                if (response != null) {
                    repository.deleteTransactions()
                    for (resp in response.records ) {
                        if (resp is PaymentOperationResponse){
                            repository.insertTransaction(Transaction(resp.id.toString(), resp.sourceAccount, resp.to, resp.amount))
                        }}
                }
            }
            catch (e: Exception){
                Log.i("addPaymentsToDBError", e.toString())
            }
        }
    }
}