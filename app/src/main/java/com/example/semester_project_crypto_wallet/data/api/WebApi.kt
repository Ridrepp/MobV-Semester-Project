package com.example.semester_project_crypto_wallet.data.api

import android.util.Log
import org.stellar.sdk.*
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.Page
import org.stellar.sdk.responses.SubmitTransactionResponse
import org.stellar.sdk.responses.operations.OperationResponse
import java.io.InputStream
import java.net.URL
import java.util.*

class WebApi() {

    fun generateKeys():KeyPair{
        val my_keypair = KeyPair.random()

        Log.i("KEYPAIR:", my_keypair.toString())
        Log.i("PUBLIC KEY:", my_keypair.accountId)
        Log.i("PRIVATE KEY:", String(my_keypair.secretSeed))

        return my_keypair
    }

    fun createAccount(my_keypair : KeyPair) {
        val friendBotUrl = String.format(
            "https://friendbot.stellar.org/?addr=%s",
            my_keypair.accountId
        )
        val response: InputStream = URL(friendBotUrl).openStream()
        Log.i("SUC", response.toString())

        val body: String = Scanner(response, "UTF-8").useDelimiter("\\A").next()
        Log.i("SUC", body)
    }

    fun getXLMBalance(publicKey: String): Float {
        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(publicKey)
        return account.balances[0].balance.toFloat()
    }

    fun sendPayment(destination: KeyPair, my_keypair : KeyPair, amount: String) {
        val server = Server("https://horizon-testnet.stellar.org")

        server.accounts().account(destination.accountId)

        val sourceAccount: AccountResponse = server.accounts().account(my_keypair.accountId)

        val transaction: Transaction? = Transaction.Builder(sourceAccount, Network.TESTNET).addOperation(
            PaymentOperation.Builder(
                destination.accountId,
                AssetTypeNative(),
                amount
            ).build()
        )
            .addMemo(Memo.text("TESTING TRANSACTION"))
            .setTimeout(180)
            .setBaseFee(Transaction.MIN_BASE_FEE)
            .build()

        transaction?.sign(my_keypair)

        try {
            val response: SubmitTransactionResponse = server.submitTransaction(transaction)
            Log.i("SUCCESS TRANSACTION", response.toString())
        } catch (e: Exception) {
            Log.i("SOMETHING WENT WRONG", e.message.toString())

        }
    }

    fun getTransactionsFromServer(publicKey: String): Page<OperationResponse>? {
        val server = Server("https://horizon-testnet.stellar.org")

        return server.payments().limit(200).forAccount(publicKey).execute()
    }
}