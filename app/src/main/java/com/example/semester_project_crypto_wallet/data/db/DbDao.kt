package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

@Dao
interface DbDao {

    @Query("SELECT * from transactions_table")
    fun getTransactions(): LiveData<List<Transaction>>

    @Query("SELECT * from receivers_table")
    suspend fun getReceivers(): List<Receiver>

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Insert
    suspend fun insertReceiver(receiver: Receiver)
}