package com.example.semester_project_crypto_wallet.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentTransactionsBinding
import kotlinx.coroutines.launch

class TxFragment : Fragment() {

    private lateinit var txViewModel: TxViewModel
    private lateinit var binding: FragmentTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        txViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(TxViewModel::class.java)

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_transactions,
                container,
                false
            )

        binding.txViewModel = txViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        lifecycleScope.launch {
            txViewModel.showTransactions()
        }

        binding.showTransactions.setOnClickListener{
            lifecycleScope.launch {
                txViewModel.showTransactions()
            }
        }


        return binding.root

    }
}