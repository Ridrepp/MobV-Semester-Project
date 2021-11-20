package com.example.semester_project_crypto_wallet.ui
import android.util.Log
import org.stellar.sdk.KeyPair
import java.io.InputStream
import java.net.URL
import java.util.*


class User() {
    val pair: KeyPair

    init {
        pair = KeyPair.random()
        Log.i("WAR",pair.toString())
        Log.i("WAR",String(pair.getSecretSeed()))
        Log.i("WAR",pair.accountId)
    }

    fun createAccount(){
        val friendbotUrl = String.format(
            "https://friendbot.stellar.org/?addr=%s",
            pair.accountId
        )
        val response: InputStream = URL(friendbotUrl).openStream()
        val body: String = Scanner(response, "UTF-8").useDelimiter("\\A").next()
//        println("SUCCESS! You have a new account :)\n$body")
        Log.i("WAR",body)

    }




}