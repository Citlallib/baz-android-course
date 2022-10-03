package com.vero.cursowizelinecriptomonedas.data.database.mapper

import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoBookDetailEntity
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload

class CryptoBookDetailDaoMapper {
    fun fromCryptoOrderEntityToDetail(cryptoOrderEntity: CryptoBookDetailEntity): CryptoBookDetailPayload {
        return CryptoBookDetailPayload(
            cryptoOrderEntity.ask,
            cryptoOrderEntity.bid,
            cryptoOrderEntity.book,
            cryptoOrderEntity.change_24,
            cryptoOrderEntity.created_at,
            cryptoOrderEntity.high,
            cryptoOrderEntity.last,
            cryptoOrderEntity.low,
            cryptoOrderEntity.volume,
            cryptoOrderEntity.vwap
        )
    }

    fun fromCryptoOrderDomainToEntity(cryptoOrder: CryptoBookDetailPayload): CryptoBookDetailEntity {
        return CryptoBookDetailEntity(
            ask = cryptoOrder.ask,
            bid = cryptoOrder.bid,
            book = cryptoOrder.book,
            change_24 = cryptoOrder.change_24,
            created_at = cryptoOrder.created_at,
            high = cryptoOrder.high,
            last = cryptoOrder.last,
            low = cryptoOrder.low,
            volume = cryptoOrder.volume,
            vwap = cryptoOrder.vwap
        )
    }
}