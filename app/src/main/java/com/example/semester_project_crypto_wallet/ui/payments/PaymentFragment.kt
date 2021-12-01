package com.example.semester_project_crypto_wallet.ui.payments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentPaymentBinding
import kotlinx.coroutines.launch

class PaymentFragment : Fragment(), AdapterView.OnItemSelectedListener{
    private lateinit var paymentsViewModel: PaymentsViewModel
    private lateinit var binding: FragmentPaymentBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        paymentsViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(PaymentsViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_payment, container, false
        )
        binding.paymentsModel = paymentsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        paymentsViewModel.balance.observe(viewLifecycleOwner, {
            binding.balanceTextView.text = getString(R.string.xlm,it.toString())
        })
        binding.sendButton.setOnClickListener{
            lifecycleScope.launch {
                if(paymentsViewModel.validatePin()){
                    val selectedReceiverName = binding.contactsDropdown.selectedItem.toString()
                    paymentsViewModel.receivers.observe(viewLifecycleOwner, { receiverName ->
                        receiverName.forEach{ it ->
                            if(it.Name == selectedReceiverName){
                                val receiverPublicKey = it.Address
                                paymentsViewModel.wallet.observe(
                                    viewLifecycleOwner,
                                    {
                                        when (paymentsViewModel.sendPayment(it.privateKey,
                                            receiverPublicKey, it.balance)){
                                            0 -> {
                                                Log.i("PaymentFragDone", "PaymentFragment: Payment done")
                                                Toast.makeText(context,"Payment was successful.",
                                                    Toast.LENGTH_LONG).show()
                                                findNavController().navigate(R.id.action_paymentFragment_to_loggedInFragment)
                                            }
                                            1 ->{
                                                Log.i("PaymentFragAmount", "PaymentFragment: Empty value of amount")
                                                Toast.makeText(context,"Amount not specified.",
                                                    Toast.LENGTH_SHORT).show()
                                            }
                                            2 ->{
                                                Log.i("PaymentFragBalance", "PaymentFragment: Not enough balance")
                                                Toast.makeText(context,"Not enough balance.",
                                                    Toast.LENGTH_SHORT).show()
                                            }
                                            else -> {
                                                Log.i("PaymentFragFailed", "PaymentFragment: Payment failed")
                                                Toast.makeText(context, "Payment failed.",
                                                    Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    })
                }
                else{
                    Log.i("PaymentFragPINBad", "PaymentFragment: PIN bad")
                    Toast.makeText(
                        context,
                        "Pin must be 4 digits long.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        binding.contactsDropdown.onItemSelectedListener = this
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contacts: MutableList<String> = ArrayList()

        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this.requireContext(), R.layout.spinner_color_layout, contacts)

        dataAdapter.setDropDownViewResource(R.layout.spinner_color_layout)
        paymentsViewModel.receivers.observe(viewLifecycleOwner, { receiverName ->
            receiverName.forEach{
                dataAdapter.add(it.Name)
            }
        })
        binding.contactsDropdown.adapter = dataAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}