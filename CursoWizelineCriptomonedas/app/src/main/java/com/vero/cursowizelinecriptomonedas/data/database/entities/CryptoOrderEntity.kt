package com.vero.cursowizelinecriptomonedas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_order_table")
data class CryptoOrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:   Int=0,
    @ColumnInfo(name = "book")
    val book: String,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "amount")
    val amount: String,
)