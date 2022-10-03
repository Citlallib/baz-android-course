package com.vero.cursowizelinecriptomonedas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoOrderEntity

@Dao
interface CryptoOrderDao {
    @Query("SELECT * FROM crypto_order_table where book == :idBook")
    suspend fun getCryptoOrder(idBook: String): List<CryptoOrderEntity>

    @Insert
    suspend fun insertCryptoOrder(cryptos: List<CryptoOrderEntity>)

    @Query("DELETE FROM crypto_order_table where book == :idBook")
    suspend fun deleteCryptoOrder(idBook: String)
}