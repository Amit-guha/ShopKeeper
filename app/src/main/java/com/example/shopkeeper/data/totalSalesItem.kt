package com.example.shopkeeper.data

import androidx.room.Embedded

data class totalSalesItem(
    val item : String,
    val price : Int,
    val quantity : Int,
    val Total : Int,

    @Embedded
    val salesIntro: salesIntro
)
