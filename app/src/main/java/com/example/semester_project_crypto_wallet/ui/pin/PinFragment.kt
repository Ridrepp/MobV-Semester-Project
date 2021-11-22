package com.example.semester_project_crypto_wallet.ui.pin

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentPinBinding

class PinFragment : Fragment(){
    private val pinViewModel: PinViewModel by viewModels()
    private lateinit var binding: FragmentPinBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pin, container, false
        )
        binding.pinModel = pinViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}