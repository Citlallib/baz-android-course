package com.vero.cursowizelinecriptomonedas.data.database.mapper

import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoImageEntity

class CryptoImageDaoMapper {
    fun fromCryptoImageEntityToDetail(cryptoImageEntity: CryptoImageEntity): String {
        return cryptoImageEntity.image
    }
    fun fromCryptoOrderDomainToEntity(cryptoBook:String, cryptoImage: String): CryptoImageEntity {
        return CryptoImageEntity(book = cryptoBook, image = cryptoImage)
    }
}