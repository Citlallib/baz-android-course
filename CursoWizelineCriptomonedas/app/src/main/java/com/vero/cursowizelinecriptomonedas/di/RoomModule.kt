package com.vero.cursowizelinecriptomonedas.di

import android.content.Context
import androidx.room.Room
import com.vero.cursowizelinecriptomonedas.data.database.CryptoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val CRYPTO_DATA_BASE = "crypto_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CryptoDataBase::class.java, CRYPTO_DATA_BASE).build()

    @Singleton
    @Provides
    fun provideCryptoRoom(db: CryptoDataBase) = db.getCryptoDao()

    @Singleton
    @Provides
    fun provideCryptoBookDetailRoom(db: CryptoDataBase) = db.getCryptoBookDetailDao()

    @Singleton
    @Provides
    fun provideCryptoOrderRoom(db: CryptoDataBase) = db.getCryptoOrderDao()

    @Singleton
    @Provides
    fun provideCryptoImageRoom(db: CryptoDataBase) = db.getCryptoImage()

}