package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            if (signInViewModel.logInAccount()) {
                //Log.i("LIVEDATA-FINDED-USER",signInViewModel.findkeycredential.toString())
                signInViewModel.findkeycredential.observe(
                    viewLifecycleOwner,
                    {
                        Log.i("FOUNDED PUBLIC", it.publicKey)
                        Log.i("FOUNDED PRIVATE", it.privateKey)
                    })


                //it.findNavController().navigate(R.id.action_signInFragment_to_loggedInFragment)
            } else
                Toast.makeText(
                    context, "Nebol zadaný public key existujúceho konta.",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }
}