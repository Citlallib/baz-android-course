package com.vero.cursowizelinecriptomonedas.data.database.mapper

import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoOrderEntity
import com.vero.cursowizelinecriptomonedas.data.model.CryptoOrder

class CryptoOrderDaoMapper {
    private fun fromCryptoOrderEntityToCryptoOrder(cryptoOrderEntity: CryptoOrderEntity): CryptoOrder{
        return CryptoOrder(
            cryptoOrderEntity.book,
            cryptoOrderEntity.price,
            cryptoOrderEntity.amount
        )
    }

    fun fromCryptoOrderListToCryptoOrderList(cryptoOrderEntity: List<CryptoOrderEntity>): List<CryptoOrder>{
        return cryptoOrderEntity.map {
            fromCryptoOrderEntityToCryptoOrder(it)
        }
    }

    private fun fromCryptoOrderToCryptoOrderEntity(cryptoOrder: CryptoOrder): CryptoOrderEntity{
        return CryptoOrderEntity(
            book = cryptoOrder.book,
            price = cryptoOrder.price,
            amount = cryptoOrder.amount
        )
    }

    fun fromCryptoOrderListToCryptoOrderEntity(cryptoOrderEntity: List<CryptoOrder>): List<CryptoOrderEntity>{
        return cryptoOrderEntity.map {
            fromCryptoOrderToCryptoOrderEntity(it)
        }
    }
}