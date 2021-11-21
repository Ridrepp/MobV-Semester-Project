package com.example.semester_project_crypto_wallet.data

import androidx.annotation.WorkerThread
import com.example.semester_project_crypto_wallet.data.db.DbDao
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

class Repository(private val dbDao: DbDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
//    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTransaction(transaction: Transaction) {
        dbDao.insertTransaction(transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertReceiver(receiver: Receiver) {
        dbDao.insertReceiver(receiver)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getTransactions() {
        dbDao.getTransactions()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getReceivers() {
        dbDao.getReceivers()
    }
}