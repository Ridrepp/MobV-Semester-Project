package com.example.semester_project_crypto_wallet.ui.transactions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.semester_project_crypto_wallet.data.db.DbDao
import com.example.semester_project_crypto_wallet.data.db.entities.Transaction
import android.util.Log

class TxViewModel (
    val database: DbDao,
    application: Application
    ) : AndroidViewModel(application)
{
    public suspend fun showTransactions()
    {
//    TODO: do something with db
//    TODO: get tx details and push to db
//    // First user
//    val first_user = User()
//    first_user.createAccount()
//    first_user.checkBalances()
//
//    //Second user
//    val second_user = User()
//    second_user.createAccount()
//    second_user.checkBalances()
//
//    Log.i("****************","********TRANSACTION*************")
//    first_user.sendPayment(second_user.my_keypair)
//
//    Log.i("****************","********BALANCES AGAIN*************")
//    first_user.checkBalances()
//    second_user.checkBalances()
//
//    Log.i("****************","********END*************")


        val transactions = listOf(
            Transaction(
                "0x67261c47e2a02043e9ec186a2d4b013647d4a55691f903bd2d07c18d551bf96e",
                "0x3cd751e6b0078be393132286c442345e5dc49699",
                "0x400d24b330b61eefab55094322f6a82f616fbc3e",
                "0.04786695",
                "0.002227805560617"
            ),
            Transaction(
                "0x26a5fa72d0c1f6fef1a835ae4fdb6152c7522b7a60dda4642aa52472578e8eb2",
                "0x216f8e5b3da5efe4b9d1b24d54b5e2d57e4b61e5",
                " 0x249e38ea4102d0cf8264d3701f1a0e39c4f2dc3b",
                "0",
                "0.004975114160774069"
            ),
            Transaction(
                "0x705c138cf3217ceaf02fb0cc359d7533d05fc1f5b6d3133f9bd6e682e9e45354 ",
                "0x216f8e5b3da5efe4b9d1b24d54b5e2d57e4b61e5",
                "0x9fa69536d1cda4a04cfb50688294de75b505a9ae",
                "0.04786695",
                "0.005187816548823454"
            )
        )


        //TODO: fix this
//        transactions.forEach { database.insertTransaction(it) }
        Log.i("tag", database.getTransactions().toString())

    }

}