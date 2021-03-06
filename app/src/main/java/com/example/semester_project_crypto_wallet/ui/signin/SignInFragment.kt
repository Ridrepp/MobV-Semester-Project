package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentSigninBinding

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
            if(!signInViewModel.checkPrivateKey()){
                Toast.makeText(context, "Private key must not be empty.", Toast.LENGTH_SHORT).show()
            }
            else if (!signInViewModel.checkPinKey()){
                Toast.makeText(context, "Pin must not be empty.", Toast.LENGTH_SHORT).show()
            }
            else if(!signInViewModel.validatePinKey()) {
                Toast.makeText(context, "Pin must be 4 digits long.", Toast.LENGTH_SHORT).show()
            }
            else if(!signInViewModel.validatePublicKeyFromPrivateKey()){
                Toast.makeText(context, "Private key is incorrect.", Toast.LENGTH_SHORT).show()

            }
            else{
                signInViewModel.insertUserToDb()
                findNavController().navigate(R.id.action_signInFragment_to_loggedInFragment)
            }
        }
    }
}