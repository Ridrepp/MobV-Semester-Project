package com.example.semester_project_crypto_wallet.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.semester_project_crypto_wallet.data.entities.Receiver
import com.example.semester_project_crypto_wallet.data.entities.Transaction

@Dao
interface DbDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * from transactions_table")
    suspend fun getTransactions(): List<Transaction>

    @Insert
    suspend fun insertReceiver(receiver: Receiver)

    @Query("SELECT * from receivers_table")
    suspend fun getReceivers(): List<Receiver>
}