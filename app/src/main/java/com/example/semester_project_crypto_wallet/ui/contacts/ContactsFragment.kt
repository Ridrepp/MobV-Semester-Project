package com.example.semester_project_crypto_wallet.ui.contacts

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
import com.example.semester_project_crypto_wallet.databinding.FragmentContactsBinding
import kotlinx.coroutines.launch

class ContactsFragment : Fragment(){
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var binding: FragmentContactsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        contactsViewModel =
            ViewModelProvider(
                this,
                Injection.provideViewModelFactory(requireContext())
            ).get(ContactsViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        binding.contactsModel = contactsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addContactButton.setOnClickListener {
            lifecycleScope.launch {
                contactsViewModel.insertContactToDatabase()
            }
        }
    }
}