package com.example.semester_project_crypto_wallet.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receivers_table")
data class Receiver (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "address")
    var Address: String,

    @ColumnInfo(name = "name")
    val Name: String
)