package com.vero.cursowizelinecriptomonedas.data.database.mapper

import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoEntity
import com.vero.cursowizelinecriptomonedas.data.model.Crypto

class CryptoDaoMapper {
    private fun fromCryptoEntityToCryptoDomain(cryptoEntity: CryptoEntity): Crypto {
        return Crypto(
            cryptoEntity.book,
            cryptoEntity.minimum_price,
            cryptoEntity.maximum_price,
            cryptoEntity.minimum_amount,
            cryptoEntity.maximum_amount,
            cryptoEntity.minimum_value,
            cryptoEntity.maximum_value,
            cryptoEntity.tick_size
        )
    }

    fun fromCryptoEntityListToCryptoDomainList(cryptoEntity: List<CryptoEntity>): List<Crypto> {
        return cryptoEntity.map { fromCryptoEntityToCryptoDomain(it) }
    }

    private fun fromCryptoDomainToCryptoEntity(crypto: Crypto): CryptoEntity {
        return CryptoEntity(
            book = crypto.book,
            minimum_price = crypto.minimum_price,
            maximum_price= crypto.maximum_price,
            minimum_amount = crypto.minimum_amount,
            maximum_amount = crypto.maximum_amount,
            minimum_value = crypto.minimum_value,
            maximum_value = crypto.maximum_value,
            tick_size = crypto.tick_size
        )
    }

    fun fromCryptoDomainListToCryptoEntityList(crypto: List<Crypto>): List<CryptoEntity> {
        return crypto.map { fromCryptoDomainToCryptoEntity(it) }
    }
}