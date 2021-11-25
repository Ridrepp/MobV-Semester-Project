package com.example.semester_project_crypto_wallet.ui.payments

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
import com.example.semester_project_crypto_wallet.databinding.FragmentPaymentBinding
import kotlinx.coroutines.launch

class PaymentFragment : Fragment(){
    private lateinit var paymentsViewModel: PaymentsViewModel
    private lateinit var binding: FragmentPaymentBinding
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
        binding.sendButton.setOnClickListener{
            lifecycleScope.launch {
                paymentsViewModel.confirmPin()
            }
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}