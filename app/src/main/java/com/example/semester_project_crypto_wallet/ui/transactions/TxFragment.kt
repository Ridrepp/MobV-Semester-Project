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
import com.example.semester_project_crypto_wallet.data.db.Db
import com.example.semester_project_crypto_wallet.databinding.FragmentTransactionsBinding
import kotlinx.coroutines.launch

class TxFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {


        val binding: FragmentTransactionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_transactions, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = Db.getInstance(application).dbDao

        val viewModelFactory = TxViewModelFactory(dataSource, application)

        val txViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(TxViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.txViewModel = txViewModel

        lifecycleScope.launch {
            txViewModel.showTransactions()
        }

//        binding.showTransactions.setOnClickListener{
//            lifecycleScope.launch {
//                txViewModel.showTransactions()
//            }
//        }

        return binding.root

    }
}