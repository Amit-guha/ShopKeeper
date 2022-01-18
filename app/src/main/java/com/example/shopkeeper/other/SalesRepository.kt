package com.example.shopkeeper.other

import androidx.lifecycle.LiveData
import com.example.shopkeeper.data.saleslist

class SalesRepository(private val salesDao: SalesDao) {

    val readall: LiveData<List<saleslist>> = salesDao.readalldata()

    suspend fun add(saleslists: saleslist){
        salesDao.adduser(saleslists)
    }

    suspend fun update(saleslists: saleslist){
        salesDao.updateUser(saleslists)
    }

    suspend fun deletedata(saleslists: saleslist){
        salesDao.deleteUser(saleslists)
    }

    suspend fun deletealluser(){
        salesDao.deletealluser()
    }

}