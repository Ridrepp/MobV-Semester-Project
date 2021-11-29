package com.example.semester_project_crypto_wallet.data.api

import android.util.Log
import org.json.JSONObject
import org.stellar.sdk.*
import org.stellar.sdk.responses.AccountResponse
import org.stellar.sdk.responses.Page
import org.stellar.sdk.responses.SubmitTransactionResponse
import org.stellar.sdk.responses.operations.OperationResponse
import org.stellar.sdk.responses.operations.PaymentOperationResponse
import org.stellar.sdk.xdr.PaymentResult
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*





class WebApi() {

    fun generateKeys():KeyPair{
        var my_keypair = KeyPair.random()

        Log.i("KEYPAIR:", my_keypair.toString())
        Log.i("PUBLIC KEY:", my_keypair.accountId)
        Log.i("PRIVATE KEY:", String(my_keypair.getSecretSeed()))

        return my_keypair
    }

    fun createAccount(my_keypair : KeyPair) {
        val friendbotUrl = String.format(
            "https://friendbot.stellar.org/?addr=%s",
            my_keypair.accountId
        )
        val response: InputStream = URL(friendbotUrl).openStream()
        Log.i("SUC", response.toString())

        val body: String = Scanner(response, "UTF-8").useDelimiter("\\A").next()
        Log.i("SUC", body)
    }

    fun getXLMbalance(publicKey: String): Float {
        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(publicKey)
        return account.balances[0].balance.toFloat()
    }

    fun checkBalances(my_keypair : KeyPair) {

        val server = Server("https://horizon-testnet.stellar.org")
        val account = server.accounts().account(my_keypair.accountId)

        Log.i("WAR", "Balances for account" + my_keypair.accountId)
        Log.i("Number of balances:", account.balances.size.toString())

        for (balance in account.balances) {
            Log.i("BALANCE", balance.balance)
            Log.i("ASSETCODE", balance.assetCode.toString())
            Log.i("ASSETTYPE", balance.assetType)
        }

        Log.i("BALANCE", account.balances[0].balance)
        Log.i("BALANCE-TYPE", account.balances[0].balance::class.simpleName.toString())
    }

    fun sendPayment(destination: KeyPair, my_keypair : KeyPair, amount: String) {
        val server = Server("https://horizon-testnet.stellar.org")

        server.accounts().account(destination.accountId)

        val sourceAccount: AccountResponse = server.accounts().account(my_keypair.getAccountId())

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

    fun getTransactionsFromServer(publicKey: String): Page<OperationResponse>? {
        val server = Server("https://horizon-testnet.stellar.org")
        val responseFromServer = server.payments().forAccount(publicKey).execute()

        return responseFromServer










    }
}