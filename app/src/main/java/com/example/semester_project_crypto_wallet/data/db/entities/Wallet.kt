package com.example.semester_project_crypto_wallet.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "publicKey")
    var publicKey: String,

    @ColumnInfo(name = "privateKey")
    val privateKey: String,

    @ColumnInfo(name = "balance")
    var balance: Float
)