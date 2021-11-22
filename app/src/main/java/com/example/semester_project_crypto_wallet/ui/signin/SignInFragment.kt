package com.example.semester_project_crypto_wallet.ui.signin

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentSigninBinding

class SignInFragment : Fragment(){
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
}