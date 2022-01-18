package com.example.shopkeeper.other

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SalesApplication : Application() {

    val applicationscope= CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { SalesDatabase.getdatabase(this) }
    val repository by lazy { SalesRepository(database.salesdao()) }
}