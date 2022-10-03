package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoList.CryptoRepository
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoDaoMapper
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    suspend operator fun invoke(): ApiResponseStatus<List<Crypto>> {
        val cryptos = repository.downloadCrypto()
        return if (cryptos is ApiResponseStatus.Success) {
            repository.clearCrypto()
            val cryptosMap = CryptoDaoMapper().fromCryptoDomainListToCryptoEntityList(
                cryptos.data
            )
            repository.insertCrypto(
                cryptosMap
            )
            return cryptos
        } else {
            repository.downloadCryptoFromDataBase()
        }
    }
}