package com.example.moviedetails

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class InventoryApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database: MovieRoomDataBase by lazy { MovieRoomDataBase.getDatabase(this) }
}
