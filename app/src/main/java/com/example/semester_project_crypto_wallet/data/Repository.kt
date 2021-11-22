package com.example.semester_project_crypto_wallet.data

import androidx.lifecycle.LiveData
import com.example.semester_project_crypto_wallet.data.db.LocalCache
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

class Repository(private val cache: LocalCache) {

    companion object {
        const val TAG = "Repository"
        @Volatile
        private var INSTANCE: Repository? = null

        fun getInstance(cache: LocalCache): Repository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Repository(cache).also { INSTANCE = it }
            }
    }

//    suspend fun getTransactions() {
//        cache.getTransactions()
//    }

    fun getTransactions(): LiveData<List<Transaction>> = cache.getTransactions()

    suspend fun getReceivers() {
        cache.getReceivers()
    }

    suspend fun insertTransaction(transaction: Transaction) {
        cache.insertTransaction(transaction)
    }

    suspend fun insertReceiver(receiver: Receiver) {
        cache.insertReceiver(receiver)
    }
}