package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.entities.Credentials
import com.example.semester_project_crypto_wallet.ui.AES
import kotlinx.coroutines.launch
import android.widget.Toast
import org.stellar.sdk.Server
import org.stellar.sdk.*
import org.stellar.sdk.requests.ErrorResponse

class SignInViewModel(private val repository: Repository) : ViewModel() {
    val publicKeyStrEditText: MutableLiveData<String> = MutableLiveData()
    val privateKeyStrEditText: MutableLiveData<String> = MutableLiveData()
    val pinKeyEditText: MutableLiveData<String> = MutableLiveData()

//    lateinit var findKeyCredential: LiveData<Credentials>
//
//    fun logInAccount() : Boolean{
//        if (publicKeyStrEditText.value =="" || publicKeyStrEditText.value == null)
//            return false
//        findKeyCredential = repository.findKeyCredentials(publicKeyStrEditText.value.toString())
//
//        return true
//    }

    fun checkPublicKey() : Boolean{
        return publicKeyStrEditText.value != null
    }

    fun checkPrivateKey() : Boolean{
        return privateKeyStrEditText.value != null
    }

    fun checkPinKey() : Boolean{
        return pinKeyEditText.value != null
    }

    fun validatePublicKey() : Boolean{

        try {
            KeyPair.fromAccountId(publicKeyStrEditText.value)
        } catch (e: Exception) {
            Log.i("validatePublicKey()", e.toString())
            return false
        }

        val server = Server("https://horizon-testnet.stellar.org")

        try {
            server.accounts().account(publicKeyStrEditText.value);
        } catch (e: ErrorResponse) {
            if (e.getCode() == 404) {
                Log.i("validatePublicKey()", e.body)
                return false
            }
        }

        return true
    }

    fun validatePinKey() : Boolean{
        if (pinKeyEditText.value?.length == 4){
            return true
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertUserToDb(){
        viewModelScope.launch {


            val encrypted_secretkey =
                pinKeyEditText.value?.let { AES.encrypt(privateKeyStrEditText.value.toString(), it) }.toString()

            try {
                repository.insertKeyPair(Credentials(publicKeyStrEditText.value.toString(),encrypted_secretkey))
                Log.i("***", "*****REGISTRATION SUCCESSFUL******")
            } catch (e: Exception) {
                Log.i("EXCEPTION", e.toString())
            }
        }
    }

}