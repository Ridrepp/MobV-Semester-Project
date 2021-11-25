package com.example.semester_project_crypto_wallet.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project_crypto_wallet.R

class ContactsAdapter(private val contactsList: ArrayList<Contacts>):
    RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contacts_list_tem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = contactsList[position]
        holder.contactName.text = currentItem.contactName
        holder.contactPK.text = currentItem.contactPK
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val contactName : TextView = itemView.findViewById(R.id.rvContactName)
        val contactPK : TextView = itemView.findViewById(R.id.rvContactPublicKey)
    }
}