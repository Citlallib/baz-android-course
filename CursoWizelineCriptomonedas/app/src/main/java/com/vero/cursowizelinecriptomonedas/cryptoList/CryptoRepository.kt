package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoDao
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoDaoMapper
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoEntity
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val cryptoDao: CryptoDao
) {

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

    suspend fun downloadCryptoFromDataBase(): ApiResponseStatus<List<Crypto>>{
        return makeNetworkCall {
            val cryptoListApiResponse = cryptoDao.getAllCrypto()
            val cryptoDTOMapper = CryptoDaoMapper()
            cryptoDTOMapper.fromCryptoEntityListToCryptoDomainList(
                cryptoListApiResponse
            )
        }
    }

    suspend fun insertCrypto(cryptos: List<CryptoEntity>){
        cryptoDao.insertAll(cryptos)
    }

    suspend fun clearCrypto(){
        cryptoDao.deleteAllCrypto()
    }
}