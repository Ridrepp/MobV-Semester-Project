package com.example.semester_project_crypto_wallet.ui.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project_crypto_wallet.R


class TxAdapter(private val transactionList: ArrayList<Tx>):
        RecyclerView.Adapter<TxAdapter.MyViewHolder>(){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_list_item, parent, false)
                return MyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
                val currentItem = transactionList[position]
                holder.transactionSource.text = currentItem.transactionSource
                holder.transactionDestination.text = currentItem.transactionDestination
                holder.transactionAmount.text = currentItem.transactionAmount
            }

            override fun getItemCount(): Int {
                return transactionList.size
            }

            class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
                val transactionSource : TextView = itemView.findViewById(R.id.textView_source)
                val transactionDestination : TextView = itemView.findViewById(R.id.textView_destination)
                val transactionAmount : TextView = itemView.findViewById(R.id.textView_amount)
            }
        }