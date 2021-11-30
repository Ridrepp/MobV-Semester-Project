package com.example.semester_project_crypto_wallet.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet

@Dao
interface DbDao {

    //Receiver
    @Query("SELECT * from receivers")
    fun getReceivers(): LiveData<List<Receiver>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceiver(receiver: Receiver)

    //Transaction
    @Query("SELECT * from transactions")
    fun getTransactions(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    //Wallet
    @Query("SELECT * from wallet")
    fun getWallet(): LiveData<Wallet>

    @Query("SELECT publicKey from wallet")
    fun getPublicKey(): LiveData<String>

    @Query("SELECT privateKey from wallet")
    fun getPrivateKey(): LiveData<String>

    @Query("SELECT balance from wallet")
    fun getBalance(): LiveData<Float>

    @Query("UPDATE wallet SET balance = :balance WHERE publicKey =:publicKey")
    suspend fun updateBalance(balance: Float?, publicKey: String)

    @Query("SELECT COUNT(publicKey) from wallet")
    fun getCountCredentials(): LiveData<Int>

    @Insert
    suspend fun insertWallet(wallet: Wallet)

    @Query("DELETE FROM wallet")
    suspend fun deleteWallet()

    @Query("DELETE FROM receivers WHERE name=:name AND address=:address")
    suspend fun deleteContact(name: String, address: String)

}