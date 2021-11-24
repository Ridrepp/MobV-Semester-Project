package com.example.semester_project_crypto_wallet.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance_table")
data class Balance (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "balance")
    var balance: Float

)