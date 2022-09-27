package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.model.Crypto
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import javax.inject.Inject

class CryptoRepository @Inject constructor() {

    suspend fun downloadCrypto(): ApiResponseStatus<List<Crypto>> {
        return makeNetworkCall {
            val cryptoListApiResponse = retrofitService.getAllCrypto()
            val cryptoDTOList = cryptoListApiResponse.payload
            val cryptoDTOMapper = CryptoDTOMapper()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                cryptoDTOList
            )
        }
    }
}