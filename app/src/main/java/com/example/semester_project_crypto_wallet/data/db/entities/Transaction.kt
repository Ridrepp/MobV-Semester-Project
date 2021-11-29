package com.example.semester_project_crypto_wallet.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "txID")
    var TxID: String,

    @ColumnInfo(name = "source")
    val Source: String,

    @ColumnInfo(name = "destination")
    var Destination: String,

    @ColumnInfo(name = "amount")
    var Amount: String,


)