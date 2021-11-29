package com.example.semester_project_crypto_wallet.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentTransactionsBinding

class TxFragment : Fragment() {

    private lateinit var txViewModel: TxViewModel
    private lateinit var binding: FragmentTransactionsBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Tx>

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
        txViewModel.publicKey.observe(viewLifecycleOwner,{publicKeyToServer->
            txViewModel.addPaymentsToDB(publicKeyToServer)

        })
        txViewModel.transactions.observe(viewLifecycleOwner, { transactionList ->
            val transactionSource = arrayListOf<String>()
            val transactionDestination = arrayListOf<String>()
            val transactionAmount = arrayListOf<String>()
            val transactionID = arrayListOf<String>()


            for (r in transactionList){
                transactionSource.add(r.Source)
                transactionDestination.add(r.Destination)
                transactionAmount.add(r.Amount)
                transactionID.add(r.TxID)
            }

            newRecyclerView = binding.transactionsRecyclerView
            newRecyclerView.layoutManager = LinearLayoutManager(this.context)
            newRecyclerView.setHasFixedSize(true)

            newArrayList = arrayListOf<Tx>()
            for (i in transactionSource.indices){
                val tx = Tx(transactionSource[i], transactionDestination[i], transactionAmount[i], transactionID[i])
                newArrayList.add(tx)
            }
            newRecyclerView.adapter = TxAdapter(newArrayList)
        })

        return binding.root

    }
}