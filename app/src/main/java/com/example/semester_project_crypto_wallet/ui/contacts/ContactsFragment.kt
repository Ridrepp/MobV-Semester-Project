package com.example.semester_project_crypto_wallet.ui.contacts

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(){
    private val contactsViewModel: ContactsViewModel by viewModels()
    private lateinit var binding: FragmentContactsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contacts, container, false
        )
        binding.contactsModel = contactsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}