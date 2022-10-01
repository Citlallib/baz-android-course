package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import javax.inject.Inject

class GetCryptoImageUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    fun getCryptoImg(crypto: String) = cryptoOrderRepository.getCryptoImg(crypto)
}