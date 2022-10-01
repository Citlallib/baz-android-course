package com.vero.cursowizelinecriptomonedas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoBookDetailEntity

@Dao
interface CryptoBookDetailDao {
    @Query("SELECT * FROM crypto_book_detail_table WHERE book == :idBook")
    suspend fun getCryptoOrderByBook(idBook: String): CryptoBookDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cryptos: CryptoBookDetailEntity)

    @Query("DELETE FROM crypto_book_detail_table")
    suspend fun delete()
}