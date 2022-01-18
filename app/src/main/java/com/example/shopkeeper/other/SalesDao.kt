package com.example.shopkeeper.other

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopkeeper.data.saleslist


@Dao
interface SalesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun adduser(saleslists: saleslist)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(saleslists: saleslist)

    @Query("SELECT * from saleslist ORDER BY Id")
    fun readalldata():LiveData<List<saleslist>>

    @Delete
    suspend fun deleteUser(saleslists: saleslist)

    @Query("Delete from saleslist")
    suspend fun deletealluser()
}