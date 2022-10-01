package com.vero.cursowizelinecriptomonedas.domain

import android.util.Log
import com.vero.cursowizelinecriptomonedas.R
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
            Log.i("okhttp", "Entra por Api")
            repository.clearCrypto()
            val cryptosMap = CryptoDaoMapper().fromCryptoDomainListToCryptoEntityList(
                cryptos.data
            )
            repository.insertCrypto(
                cryptosMap
            )
            return cryptos
        } else {
            Log.i("okhttp", "Entra por DB")
            repository.downloadCryptoFromDataBase()
        }
    }
}