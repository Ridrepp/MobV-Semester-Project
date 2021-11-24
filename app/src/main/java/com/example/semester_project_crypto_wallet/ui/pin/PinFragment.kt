package com.example.semester_project_crypto_wallet.ui.pin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentPinBinding
import kotlinx.coroutines.launch

class PinFragment : Fragment(){
    private val pinViewModel: PinViewModel by viewModels()
    private lateinit var binding: FragmentPinBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pin, container, false
        )
        binding.pinModel = pinViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //TODO: check if it is supposed to be onCreateView or onViewCreated
        binding.button2.setOnClickListener{
            lifecycleScope.launch {
                pinViewModel.confirmPin()
            }
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        pinViewModel.pin.observe(viewLifecycleOwner){
//            Log.i("pin","$it")
//        }

    }
}