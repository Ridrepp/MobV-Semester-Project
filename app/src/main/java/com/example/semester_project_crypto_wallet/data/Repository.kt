package com.example.semester_project_crypto_wallet.data

import androidx.lifecycle.LiveData
import com.example.semester_project_crypto_wallet.data.db.LocalCache
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
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

    //Balance
    fun getBalance(): LiveData<Float> = cache.getBalance()

    //Keypair
    fun getPublicKey(): LiveData<String> = cache.getPublicKey()

    fun getPrivateKey(): LiveData<String> = cache.getPrivateKey()

    suspend fun insertKeyPair(credentials: Credentials) {
        cache.insertKeyPair(credentials)
    }

//    suspend fun getTransactions() {
//        cache.getTransactions()
//    }

    fun getTransactions(): LiveData<List<Transaction>> = cache.getTransactions()
    fun getReceivers(): LiveData<List<Receiver>> = cache.getReceivers()


    suspend fun insertTransaction(transaction: Transaction) {
        cache.insertTransaction(transaction)
    }

    suspend fun insertReceiver(receiver: Receiver) {
        cache.insertReceiver(receiver)
    }
}