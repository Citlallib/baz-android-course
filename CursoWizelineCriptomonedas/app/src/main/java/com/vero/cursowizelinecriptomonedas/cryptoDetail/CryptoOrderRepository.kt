package com.vero.cursowizelinecriptomonedas.cryptoDetail

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoOrderDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import com.vero.cursowizelinecriptomonedas.model.CryptoBookDetail
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class CryptoOrderRepository @Inject constructor() {
    //TODO : ApiResponseStatus
    suspend fun downloadCryptoOrder(crypto: String): ApiResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoOrderListApiResponde = retrofitService.getOrderCrypto(crypto)
            val cryptoOrderDTOList = cryptoOrderListApiResponde.payload.asks
            val cryptoOrderDTOMapper = CryptoOrderDTOMapper()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderDTOList)
        }
    }

    fun getDetailBook(crypto: String): Single<Response<CryptoBookDetail>> {
        return retrofitService.getDeatilCrypto(crypto)
    }

    fun getCryptoImg(crypto: String): String =
        "https://firebasestorage.googleapis.com/v0/b/crypto-d6420.appspot.com/o/cryptocurrency_icon%2Fic_crypto_${
            crypto.split("_").get(0)
        }.png?alt=media"

}