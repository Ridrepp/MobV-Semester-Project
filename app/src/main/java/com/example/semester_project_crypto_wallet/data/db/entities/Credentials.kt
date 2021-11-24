package com.example.semester_project_crypto_wallet.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credentials_table")
data class Credentials (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "publicKey")
    var publicKey: String,

    @ColumnInfo(name = "privateKey")
    val privateKey: String
)