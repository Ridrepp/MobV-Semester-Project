package com.example.semester_project_crypto_wallet
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.semester_project_crypto_wallet.databinding.ActivityMainBinding
import org.stellar.sdk.KeyPair
import android.util.Log
import java.net.*;
import java.io.*;
import java.util.*;


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