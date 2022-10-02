package com.vero.cursowizelinecriptomonedas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_image_table")
data class CryptoImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:   Int=0,
    @ColumnInfo(name = "book")
    val book: String,
    @ColumnInfo(name = "image")
    val image: String
)
