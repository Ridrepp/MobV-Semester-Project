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
        Log.i("PaymentViewModelPin", "PaymentsViewModel: validatePin(): " + pin.value?.length)
        if (pin.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendPayment(sourcePrivateKey: String, destinationPublicKey: String, balance: Float):Int {
        try {
            val pin = pin.value.toString()
            val amountText = amount.value
            val amount = amount.value.toString()
            val myKeypair = KeyPair.fromSecretSeed(AES.decrypt(sourcePrivateKey, pin))
            val destinationKeypair = KeyPair.fromAccountId(destinationPublicKey)

            if(amountText == null){
                return 1
            }
            if(amount.toFloat() > balance){
                return 2
            }
            api.sendPayment(destinationKeypair, myKeypair, amount)

            return 0
        }
        catch (e: Exception){
            Log.i("sendPaymentError:", e.toString())
            return -1
        }
    }

}