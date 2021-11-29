package com.example.semester_project_crypto_wallet.data

import androidx.lifecycle.LiveData
import com.example.semester_project_crypto_wallet.data.db.LocalCache
import com.example.semester_project_crypto_wallet.data.db.entities.*

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

    //Receiver
    fun getReceivers(): LiveData<List<Receiver>> = cache.getReceivers()

    suspend fun insertReceiver(receiver: Receiver) {
        cache.insertReceiver(receiver)
    }

    //Transaction
    fun getTransactions(): LiveData<List<Transaction>> = cache.getTransactions()

    suspend fun insertTransaction(transaction: Transaction) {
        cache.insertTransaction(transaction)
    }

    //Wallet
    fun getWallet(): LiveData<Wallet> = cache.getWallet()

    fun getPublicKey(): LiveData<String> = cache.getPublicKey()

    fun getPrivateKey(): LiveData<String> = cache.getPrivateKey()

    fun getBalance(): LiveData<Float> = cache.getBalance()

    suspend fun updateBalance(balance: Float?, publicKey: String){
        cache.updateBalance(balance, publicKey)
    }

    fun getCountCredentials(): LiveData<Int> = cache.getCountCredentials()

    suspend fun insertWallet(wallet: Wallet){
        cache.insertWallet(wallet)
    }

    suspend fun deleteWallet() = cache.deleteWallet()

}