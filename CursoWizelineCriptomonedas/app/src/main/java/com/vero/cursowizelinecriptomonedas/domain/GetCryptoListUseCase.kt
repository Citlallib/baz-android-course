package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.cryptoList.CryptoRepository
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    suspend operator fun invoke() = repository.downloadCrypto()
}