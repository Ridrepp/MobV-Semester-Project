package com.example.semester_project_crypto_wallet.ui.logged_in

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentLoggedInBinding
import com.example.semester_project_crypto_wallet.ui.transactions.TxViewModel
import kotlinx.coroutines.launch

class LoggedInFragment : Fragment() {
    private lateinit var loggedInViewModel: LoggedInViewModel
    private lateinit var binding: FragmentLoggedInBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loggedInViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(LoggedInViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_logged_in, container, false
        )
        binding.loggedInModel = loggedInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

//        //TODO: check db credentials
//        if(!loggedInViewModel.checkSession()){
//            Log.i("LoggedInFragment", "checkSession() false")
////            findNavController().navigate(R.id.action_loggedInFragment_to_homeFragment)
//        }

        loggedInViewModel.countCredentials.observe(
            viewLifecycleOwner,
            {
                lifecycleScope.launch {
                    loggedInViewModel.checkSession()
                }
            }
        )

        lifecycleScope.launch {
            loggedInViewModel.checkSession()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transactionsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loggedInFragment_to_txFragment)
        }
        binding.paymentButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loggedInFragment_to_paymentFragment)
        }
        binding.addContactButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loggedInFragment_to_contactsFragment)
        }
        binding.loggedOutButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loggedInFragment_to_homeFragment)
        }
    }
}