package com.example.semester_project_crypto_wallet.ui.register

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        registerViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(RegisterViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )
        binding.confirmRegister.isEnabled = false
        binding.registerModel = registerViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // GENERATE BUTTON LISTENER
        binding.generateKeys.setOnClickListener {
            lifecycleScope.launch {
                registerViewModel.generateKeyPair()
            }
            binding.publicKeyText.animation = AnimationUtils.loadAnimation(this.context, R.anim.fade_in)
            binding.privateKeyText.animation = AnimationUtils.loadAnimation(this.context, R.anim.fade_in)
            binding.publicKeyTextView.visibility = View.VISIBLE
            binding.privateKeyTextView.visibility = View.VISIBLE
            binding.confirmRegister.isEnabled = true
        }

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmRegister.setOnClickListener {
            lifecycleScope.launch {

                Log.i("mobv", "RegisterFragment: pressed confirmRegister")
                if (registerViewModel.validatePin()) {
                    Log.i("mobv", "RegisterFragment: PIN ok")
                    registerViewModel.insertUserToDb()
                    Log.i("mobv", "RegisterFragment: wallet created and user inserted")
                    it.findNavController().navigate(R.id.action_registerFragment_to_loggedInFragment)
                }else{
                    Log.i("mobv", "RegisterFragment: PIN bad")
                    Toast.makeText(
                        context,
                        "Pin must be 4 digits long.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}