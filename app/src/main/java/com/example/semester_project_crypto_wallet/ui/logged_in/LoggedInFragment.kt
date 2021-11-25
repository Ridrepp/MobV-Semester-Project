package com.example.semester_project_crypto_wallet.ui.logged_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentLoggedInBinding

class LoggedInFragment : Fragment() {
    private val loggedInViewModel: LoggedInViewModel by viewModels()
    private lateinit var binding: FragmentLoggedInBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_logged_in, container, false
        )
        binding.loggedInModel = loggedInViewModel
        binding.lifecycleOwner = viewLifecycleOwner

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
    }
}