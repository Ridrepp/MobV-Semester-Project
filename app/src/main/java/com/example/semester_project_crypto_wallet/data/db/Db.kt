package com.example.semester_project_crypto_wallet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.semester_project_crypto_wallet.data.db.entities.*

@Database(
    entities =
    [
        Transaction::class,
        Receiver::class,
        Wallet::class
    ],
    version = 3,
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