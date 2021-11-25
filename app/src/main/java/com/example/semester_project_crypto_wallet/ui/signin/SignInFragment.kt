package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentSigninBinding

class SignInFragment: Fragment() {
    private val signInViewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSigninBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_signin, container, false
        )
        binding.signInModel = signInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {it.findNavController().navigate(R.id.action_signInFragment_to_loggedInFragment)}
    }
}