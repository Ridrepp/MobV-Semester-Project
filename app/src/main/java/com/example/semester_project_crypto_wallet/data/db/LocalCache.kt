package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

class LocalCache(private val dao: DbDao) {

    //Balance
    fun getBalance(): LiveData<Float> = dao.getBalance()

    //Keypair
    fun getPublicKey(): LiveData<String> = dao.getPublicKey()

    fun getPrivateKey(): LiveData<String> = dao.getPrivateKey()

    fun getCountCredentials(): LiveData<Int> = dao.getCountCredentials()

    suspend fun insertKeyPair(credentials: Credentials) {
        dao.insertKeyPair(credentials)
    }

    fun getCredentials(): LiveData<List<Credentials>> = dao.getCredentials()

    fun findKeyCredentials(findKey: String): LiveData<Credentials> = dao.findKeyCredentials(findKey)

    //Transactions
    fun getTransactions(): LiveData<List<Transaction>> = dao.getTransactions()

    suspend fun insertTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    //Receivers
    fun getReceivers(): LiveData<List<Receiver>> = dao.getReceivers()

    suspend fun insertReceiver(receiver: Receiver) {
        dao.insertReceiver(receiver)
    }
}