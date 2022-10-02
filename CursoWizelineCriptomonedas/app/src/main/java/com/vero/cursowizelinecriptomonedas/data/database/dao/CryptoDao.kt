package com.vero.cursowizelinecriptomonedas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoEntity

@Dao
interface CryptoDao {
    @Query("SELECT * FROM crypto_table")
    suspend fun getAllCrypto(): List<CryptoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptos: List<CryptoEntity>)

    @Query("DELETE FROM crypto_table")
    suspend fun deleteAllCrypto()
}