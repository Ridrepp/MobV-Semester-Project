package com.example.semester_project_crypto_wallet

import android.util.Log
import org.stellar.sdk.*
import org.stellar.sdk.Transaction.Builder
import org.stellar.sdk.Transaction.MIN_BASE_FEE
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.SubmitTransactionResponse
import java.io.InputStream
import java.net.URL
import java.util.*


class User() {
    val my_keypair: KeyPair

    init {
        my_keypair = KeyPair.random()
        Log.i("WAR", my_keypair.toString())
        Log.i("WAR", String(my_keypair.getSecretSeed()))
        Log.i("WAR", my_keypair.accountId)
    }

    // This function creates and funds account from generated keypairs above with 10,000 test XLM
    fun createAccount() {
        val friendbotUrl = String.format(
            "https://friendbot.stellar.org/?addr=%s",
            my_keypair.accountId
        )
        val response: InputStream = URL(friendbotUrl).openStream()
        Log.i("WAR", "After response")
        Log.i("SUC", response.toString())
        val body: String = Scanner(response, "UTF-8").useDelimiter("\\A").next()
//        println("SUCCESS! You have a new account :)\n$body")
        Log.i("SUC", body)

    }

    fun checkBalances() {
        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(my_keypair.accountId)
        Log.i("WAR", "Balances for account" + my_keypair.accountId)
        for (balance in account.balances) {
            Log.i("BALANCE", balance.balance)
            Log.i("ASSETCODE", balance.assetCode.toString())
            Log.i("ASSETTYPE", balance.assetType)

        }
    }

    fun sendPayment(destination: KeyPair) {
        val server = Server("https://horizon-testnet.stellar.org")

        server.accounts().account(destination.accountId)

        val sourceAccount: AccountResponse = server.accounts().account(my_keypair.getAccountId())

        val transaction: Transaction? = Builder(sourceAccount, Network.TESTNET).addOperation(
            PaymentOperation.Builder(
                destination.accountId,
                AssetTypeNative(),
                "10"
            ).build()
        )
            .addMemo(Memo.text("TESTING TRANSACTION"))
            .setTimeout(180)
            .setBaseFee(MIN_BASE_FEE)
            .build()

        if (transaction != null) {
            transaction.sign(my_keypair)
        }

        try {
            val response: SubmitTransactionResponse = server.submitTransaction(transaction)
            Log.i("SUCCESS TRANSACTION", response.toString())
        } catch (e: Exception) {
            Log.i("SOMETHING WENT WRONG", e.message.toString())

        }


    }
}