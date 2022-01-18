package com.example.shopkeeper.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class saleslist(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val customername : String,
    val phone : String,
    val address : String,
    val total : String,
    val paid : String,
    val due : String
):Parcelable
