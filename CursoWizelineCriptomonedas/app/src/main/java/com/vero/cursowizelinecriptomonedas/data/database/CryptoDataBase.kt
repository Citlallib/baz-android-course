package com.vero.cursowizelinecriptomonedas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoBookDetailDao
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoDao
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoOrderDao
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoBookDetailEntity
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoEntity
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoOrderEntity

@Database(entities = [CryptoEntity::class, CryptoBookDetailEntity::class, CryptoOrderEntity::class],  version = 2)
abstract class CryptoDataBase: RoomDatabase() {
    abstract fun getCryptoDao(): CryptoDao
    abstract fun getCryptoBookDetailDao(): CryptoBookDetailDao
    abstract fun getCryptoOrderDao(): CryptoOrderDao
}
