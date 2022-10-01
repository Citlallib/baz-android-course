package com.vero.cursowizelinecriptomonedas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_book_detail_table")
class CryptoBookDetailEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:             Int=0,
    @ColumnInfo(name = "ask") val ask: String,
    @ColumnInfo(name = "bid")val bid: String,
    @ColumnInfo(name = "book")val book: String,
    @ColumnInfo(name = "change_24")val change_24: String,
    @ColumnInfo(name = "created_at")val created_at: String,
    @ColumnInfo(name = "high")val high: String,
    @ColumnInfo(name = "last")val last: String,
    @ColumnInfo(name = "low")val low: String,
    @ColumnInfo(name = "volume")val volume: String,
    @ColumnInfo(name = "vwap")val vwap: String
    )