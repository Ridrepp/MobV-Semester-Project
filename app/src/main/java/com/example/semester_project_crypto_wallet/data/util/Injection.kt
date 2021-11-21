package com.example.semester_project_crypto_wallet.data.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.semester_project_crypto_wallet.data.Repository
import com.example.semester_project_crypto_wallet.data.db.Db
import com.example.semester_project_crypto_wallet.data.db.LocalCache

object Injection {

    private fun provideCache(context: Context): LocalCache {
        val database = Db.getInstance(context)
        return LocalCache(database.dbDao)
    }

    private fun provideDataRepository(context: Context): Repository {
        return Repository.getInstance(provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(
            provideDataRepository(
                context
            )
        )
    }
}