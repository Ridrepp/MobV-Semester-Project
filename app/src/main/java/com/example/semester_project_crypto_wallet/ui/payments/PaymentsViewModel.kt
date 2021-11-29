package com.example.semester_project_crypto_wallet.ui.payments

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.api.WebApi
import com.example.semester_project_crypto_wallet.data.db.entities.Receiver
import com.example.semester_project_crypto_wallet.ui.AES
import org.stellar.sdk.KeyPair

class PaymentsViewModel(
    private val repository: Repository
) : ViewModel() {

    val api: WebApi = WebApi()

    val receivers: LiveData<List<Receiver>>
        get() = repository.getReceivers()
    val amount: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()

    val publicKey: LiveData<String>
        get() = repository.getPublicKey()

    val privateKey: LiveData<String>
        get() = repository.getPrivateKey()

    fun validatePin(): Boolean{
        Log.i("mobv", "PaymentsViewModel: validatePin(): " + pin.value?.length)
        if (pin.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendPayment() {
        Log.i("mobv", "PaymentsViewModel: sendPayment()")

        // TODO: destinationPublicKey from drop down
//        destinationPublicKey =
        val sourcePublicKey = publicKey.value.toString()
        val sourcePrivateKey = privateKey.value.toString()
        val pin = pin.value.toString()
        val amount = amount.value.toString()

        Log.i("mobv", sourcePublicKey)
        Log.i("mobv", sourcePrivateKey)
        Log.i("mobv", pin)
        Log.i("mobv", amount)

//        var destination_keypair = KeyPair.fromAccountId(destinationPublicKey)
        val decrypted_secretkey = AES.decrypt(sourcePrivateKey, pin)
        if (decrypted_secretkey != null) {
            Log.i("mobv", decrypted_secretkey)
        }
//        val my_keypair = KeyPair(sourcePublicKey, decrypted_secretkey)
        // TODO: constructor requires ed25519

//        api.sendPayment(destination_keypair, my_keypair, amount)
    }

}