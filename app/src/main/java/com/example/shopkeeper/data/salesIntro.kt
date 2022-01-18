package com.example.shopkeeper.data

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class salesIntro(
    val id: Int,
    val Customername: String,
    val CustomerPhone: String,
    val CustomerAddress: String
) : Parcelable

