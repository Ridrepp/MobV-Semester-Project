package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

class LocalCache(private val dao: DbDao) {

    //Balance
    fun getBalance(): LiveData<Float> = dao.getBalance()

    //Keypair
    fun getPublicKey(): LiveData<String> = dao.getPublicKey()

    fun getPrivateKey(): LiveData<String> = dao.getPrivateKey()

    suspend fun insertKeyPair(credentials: Credentials) {
        dao.insertKeyPair(credentials)
    }

//    suspend fun getTransactions() {
//        dao.getTransactions()
//    }

    fun getTransactions(): LiveData<List<Transaction>> = dao.getTransactions()

    suspend fun getReceivers() {
        dao.getReceivers()
    }

    suspend fun insertTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    suspend fun insertReceiver(receiver: Receiver) {
        dao.insertReceiver(receiver)
    }
}