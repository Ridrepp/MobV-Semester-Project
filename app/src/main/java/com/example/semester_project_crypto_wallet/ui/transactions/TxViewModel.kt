package com.example.semester_project_crypto_wallet.ui.transactions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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

    val api: WebApi = WebApi()

    val transactions: LiveData<List<Transaction>>
        get() = repository.getTransactions()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    val transactions_as_text: LiveData<String> = Transformations.map(transactions){
        var transactions_text = ""
        it?.forEach {
            transactions_text += it.toString() + ", "
        }
        transactions_text
    }

    fun addPaymentsToDB(publicKey: String) {
        viewModelScope.launch {
            try {
                Log.i("TxViewModel", "correct")
                val response = api.getTransactionsFromServer(publicKey)
//                lateinit var all_payments: MutableList<Transaction>


                // TODO: SOMETHING WRONG HERE, ONLY INSERTING FIRST LINE OF PaymentOperationResponse TO DB
                if (response != null) {
                    for (resp in response.records ) {
                        if (resp is PaymentOperationResponse){
                            repository.insertTransaction(Transaction(resp.id.toString(), resp.sourceAccount, resp.to, resp.amount))
                        }}
                }


            }catch (e: Exception){
                Log.i("TxViewModel", "something went wrong")
                Log.i("EXCEPTION", e.toString())
            }
        }



    }

//    fun insertTransactionsToDatabase(publicKey: String){
//        try {
//
//        }
//        catch (e:java.lang.Exception){
//            Log.i("InsertTransException", e.toString())
//        }
//    }

    fun showTransactions() {
        viewModelScope.launch { Log.i("getTransactions()", repository.getTransactions().toString()) }
    }

}