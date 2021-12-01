package com.example.semester_project_crypto_wallet.ui

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

// https://howtodoinjava.com/java/java-security/java-aes-encryption-example/

object AES {
    private var secretKey: SecretKeySpec? = null

    private lateinit var key: ByteArray

    private fun setKey(myKey: String) {
        var sha: MessageDigest? = null
        try {
            key = myKey.toByteArray(charset("UTF-8"))
            sha = MessageDigest.getInstance("SHA-1")
            key = sha.digest(key)
            key = Arrays.copyOf(key, 16)
            secretKey = SecretKeySpec(key, "AES")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(strToEncrypt: String, secret: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            return Base64.getEncoder()
                .encodeToString(cipher.doFinal(strToEncrypt.toByteArray(charset("UTF-8"))))
        } catch (e: Exception) {
            println("Error while encrypting: $e")
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(strToDecrypt: String?, secret: String): String? {
        try {
            setKey(secret)
            val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            return String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)))
        } catch (e: Exception) {
            println("Error while decrypting: $e")
        }
        return null
    }
}