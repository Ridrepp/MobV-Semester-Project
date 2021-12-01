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
import com.example.semester_project_crypto_wallet.data.db.entities.Wallet
import com.example.semester_project_crypto_wallet.ui.AES
import org.stellar.sdk.KeyPair

class PaymentsViewModel(
    private val repository: Repository
) : ViewModel() {

    val api: WebApi = WebApi()

    val balance:LiveData<Float>
        get() = repository.getBalance()

    val wallet: LiveData<Wallet>
        get() = repository.getWallet()
    val receivers: LiveData<List<Receiver>>
        get() = repository.getReceivers()
    val amount: MutableLiveData<String> = MutableLiveData()
    val pin: MutableLiveData<String> = MutableLiveData()

    fun validatePin(): Boolean{
        Log.i("mobv", "PaymentsViewModel: validatePin(): " + pin.value?.length)
        if (pin.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendPayment(sourcePublicKey: String, sourcePrivateKey: String, destinationPublicKey: String):Boolean {
        try {

        Log.i("mobv", "PaymentsViewModel: sendPayment()")

            val pin = pin.value.toString()
            val amount = amount.value.toString()
            val my_keypair = KeyPair.fromSecretSeed(AES.decrypt(sourcePrivateKey, pin))
            val destination_keypair = KeyPair.fromAccountId(destinationPublicKey)

    //        Log.i("mobv", sourcePublicKey)
    //        Log.i("mobv", sourcePrivateKey)
    //        Log.i("mobv", destinationPublicKey)
    //        Log.i("mobv", pin)
    //        Log.i("mobv", amount)
    //        Log.i("mobv", my_keypair.accountId)
    //        Log.i("mobv", String(my_keypair.secretSeed))
    //        Log.i("mobv", my_keypair.canSign().toString())
    //        Log.i("mobv", destination_keypair.accountId)
    //        Log.i("mobv", destination_keypair.canSign().toString())
            api.sendPayment(destination_keypair, my_keypair, amount)

            return true
        }
        catch (e: Exception){
            Log.i("sendPaymentErr", e.toString())
            return false
        }
    }

}