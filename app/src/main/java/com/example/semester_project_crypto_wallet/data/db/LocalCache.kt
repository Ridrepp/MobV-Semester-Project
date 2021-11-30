package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet

class LocalCache(private val dao: DbDao) {

    //Receiver
    fun getReceivers(): LiveData<List<Receiver>> = dao.getReceivers()

    suspend fun insertReceiver(receiver: Receiver) {
        dao.insertReceiver(receiver)
    }

    //Transaction
    fun getTransactions(): LiveData<List<Transaction>> = dao.getTransactions()

    suspend fun insertTransaction(transaction: Transaction) {
        dao.insertTransaction(transaction)
    }

    //Wallet
    fun getWallet(): LiveData<Wallet> = dao.getWallet()

    fun getPublicKey(): LiveData<String> = dao.getPublicKey()

    fun getPrivateKey(): LiveData<String> = dao.getPrivateKey()

    fun getBalance(): LiveData<Float> = dao.getBalance()

    suspend fun updateBalance(balance: Float?, publicKey: String){
        dao.updateBalance(balance, publicKey)
    }

    fun getCountCredentials(): LiveData<Int> = dao.getCountCredentials()

    suspend fun insertWallet(wallet: Wallet){
        dao.insertWallet(wallet)
    }

    suspend fun deleteWallet() = dao.deleteWallet()

    suspend fun deleteContact(name: String, address: String) = dao.deleteContact(name, address)

    suspend fun deleteTransactions() = dao.deleteTransactions()

}