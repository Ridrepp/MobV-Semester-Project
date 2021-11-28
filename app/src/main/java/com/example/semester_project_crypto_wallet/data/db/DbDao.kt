package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.semester_project_crypto_wallet.data.db.entities.Balance
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction

@Dao
interface DbDao {

    //Balance
    @Query("SELECT * from balance_table")
    fun getBalance(): LiveData<Float>

    //Keypair
    @Query("SELECT publicKey from credentials_table")
    fun getPublicKey(): LiveData<String>

    @Query("SELECT privateKey from credentials_table")
    fun getPrivateKey(): LiveData<String>

    @Query("SELECT COUNT(publicKey) from credentials_table")
    fun getCountCredentials(): LiveData<Int>

    @Query("SELECT * from credentials_table")
    fun getCredentials(): LiveData<List<Credentials>>

    @Query("SELECT * from credentials_table WHERE publicKey = :findKey")
    fun findKeyCredentials(findKey:String): LiveData<Credentials>

    @Insert
    suspend fun insertKeyPair(credentials: Credentials)

    @Insert
    suspend fun insertBalance(balance: Balance)

    @Query("DELETE FROM credentials_table")
    suspend fun clearCredentials()

    //Transactions
    @Query("SELECT * from transactions_table")
    fun getTransactions(): LiveData<List<Transaction>>

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    //Receivers
    @Query("SELECT * from receivers_table")
    fun getReceivers(): LiveData<List<Receiver>>

    @Insert
    suspend fun insertReceiver(receiver: Receiver)

}