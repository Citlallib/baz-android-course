package com.vero.cursowizelinecriptomonedas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoImageEntity

@Dao
interface CryptoImageDao {
    @Query("SELECT * FROM crypto_image_table WHERE book == :idBook")
    suspend fun getCryptoImage(idBook: String): CryptoImageEntity

    @Insert
    suspend fun insertCryptoImage(crypto: CryptoImageEntity)

    @Query("DELETE FROM crypto_image_table WHERE book == :idBook")
    suspend fun deleteCryptoImage(idBook: String)
}