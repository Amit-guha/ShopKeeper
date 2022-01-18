package com.example.shopkeeper.data

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class updateItem (

    val updateid: Int,
    val updateCustomername: String,
    val updateCustomerPhone: String,
    val updateCustomerAddress: String,
    val updatetotal : String,
    val updatepaid : String,
    val updatedue : String
    ) :Parcelable