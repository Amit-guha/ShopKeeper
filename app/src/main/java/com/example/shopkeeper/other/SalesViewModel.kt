package com.example.shopkeeper.other

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.shopkeeper.data.saleslist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class SalesViewModel(application: Application) : AndroidViewModel(application) {

    val  alldata : LiveData<List<saleslist>>
    private val repository: SalesRepository

    init {
        val Dao = SalesDatabase.getdatabase(application).salesdao()
        repository= SalesRepository(Dao)
        alldata=repository.readall
    }

    fun adduser(saleslists: saleslist){
        viewModelScope.launch(Dispatchers.IO){
            repository.add(saleslists)
        }
    }

    fun updateuser(saleslists: saleslist){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(saleslists)
        }
    }

    fun deleteuser(saleslists: saleslist){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletedata(saleslists)
        }
    }

    fun deleteallu(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deletealluser()
        }
    }
}