package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentSigninBinding
import kotlinx.coroutines.launch

class SignInFragment : Fragment() {
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        signInViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(SignInViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signin, container, false
        )

        binding.signInModel = signInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
//            if (signInViewModel.logInAccount()) {
//                signInViewModel.findKeyCredential.observe(
//                    viewLifecycleOwner,
//                    {
//                        if (it == null){
//                            Toast.makeText(context, "Konto so zadaným public key nebolo nájdené.", Toast.LENGTH_LONG).show()
//                        }
//                        else {
//                            Log.i("FOUNDED PUBLIC", it.publicKey)
//                            Log.i("FOUNDED PRIVATE", it.privateKey)
//
//                            findNavController().navigate(R.id.action_signInFragment_to_loggedInFragment)
//                        }
//                    })
//            } else
//                Toast.makeText(
//                    context, "Nebol zadaný public key.",
//                    Toast.LENGTH_SHORT
//                ).show()

            if(!signInViewModel.checkPublicKey() || !signInViewModel.checkPrivateKey() || !signInViewModel.checkPinKey()){
                Toast.makeText(context, "Chybaju udaje", Toast.LENGTH_LONG).show()
            }else if(!signInViewModel.validatePublicKey()){
                Toast.makeText(context, "Chybny public key", Toast.LENGTH_LONG).show()
            }else if(!signInViewModel.validatePinKey()){
                Toast.makeText(context, "kratky pin", Toast.LENGTH_LONG).show()
            }else{
                signInViewModel.insertUserToDb()
                findNavController().navigate(R.id.action_signInFragment_to_loggedInFragment)
            }

        }
    }
}