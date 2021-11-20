package com.example.semester_project_crypto_wallet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.semester_project_crypto_wallet.data.entities.Receiver
import com.example.semester_project_crypto_wallet.data.entities.Transaction

@Database(
    entities =
    [
        Transaction::class,
        Receiver::class
    ],
    version = 1,
    exportSchema = false)
abstract class Db : RoomDatabase() {

    abstract val dbDao: DbDao

    companion object {
        @Volatile
        private var INSTANCE: Db? = null

        fun getInstance(context: Context): Db {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        Db::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}