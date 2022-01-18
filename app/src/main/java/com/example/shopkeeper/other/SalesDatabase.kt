package com.example.shopkeeper.other

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopkeeper.data.saleslist

@Database(entities = [saleslist::class],version = 1,exportSchema = false)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun salesdao() : SalesDao

    companion object {
        @Volatile
        private var INSTNCE: SalesDatabase? = null


        fun getdatabase(context: Context): SalesDatabase {
            val temp = INSTNCE
            if (temp != null) {
                return temp
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SalesDatabase::class.java,
                    "saleslist"
                ).build()
                INSTNCE = instance
                return instance
            }
        }
    }
}