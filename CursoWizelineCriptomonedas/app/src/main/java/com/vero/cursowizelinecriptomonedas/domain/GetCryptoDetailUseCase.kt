package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import javax.inject.Inject

class GetCryptoDetailUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    suspend operator fun invoke(crypto: String) = cryptoOrderRepository.downloadCryptoOrder(crypto)

    fun getDetailBook(crypto: String)= cryptoOrderRepository.getDetailBook(crypto)

    fun getCryptoImg(crypto: String) = cryptoOrderRepository.getCryptoImg(crypto)
}