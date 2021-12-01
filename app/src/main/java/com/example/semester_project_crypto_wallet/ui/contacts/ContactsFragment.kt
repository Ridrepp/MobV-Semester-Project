package com.example.semester_project_crypto_wallet.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project_crypto_wallet.R
import com.example.semester_project_crypto_wallet.data.util.Injection
import com.example.semester_project_crypto_wallet.databinding.FragmentContactsBinding
import kotlinx.coroutines.launch

class ContactsFragment : Fragment(){
    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var binding: FragmentContactsBinding
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Contacts>

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
        contactsViewModel.receivers.observe(viewLifecycleOwner, { receiverList ->
            val contactsName = arrayListOf<String>()
            val contactsPK = arrayListOf<String>()

            for (r in receiverList){
                contactsName.add(r.Name)
                contactsPK.add(r.Address)
            }

            newRecyclerView = binding.contactsRecyclerView
            newRecyclerView.layoutManager = LinearLayoutManager(this.context)
            newRecyclerView.setHasFixedSize(true)

            newArrayList = arrayListOf()
            for (i in contactsName.indices){
                val contacts = Contacts(contactsName[i], contactsPK[i])
                newArrayList.add(contacts)
            }
            val cAdapter = ContactsAdapter(newArrayList)
            cAdapter.setOnItemClickListener(object : ContactsAdapter.OnItemClickListener {

                override fun onDeleteClick(position: Int) {
                    contactsViewModel.deleteContactFromDB(contactsName[position], contactsPK[position])
                    binding.contactsRecyclerView.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                }
            })
            newRecyclerView.adapter = cAdapter
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addContactButton.setOnClickListener {
            lifecycleScope.launch {
                when(contactsViewModel.insertContactToDatabase()){
                    0 -> { binding.contactsRecyclerView.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in) }
                    1 -> { Toast.makeText(context, "Contact name and public key can not be empty.", Toast.LENGTH_SHORT).show() }
                    2 -> { Toast.makeText(context, "Public key can not be empty.", Toast.LENGTH_SHORT).show() }
                    3 -> { Toast.makeText(context, "Contact name can not be empty.", Toast.LENGTH_SHORT).show() }
                }
            }
        }
    }
}