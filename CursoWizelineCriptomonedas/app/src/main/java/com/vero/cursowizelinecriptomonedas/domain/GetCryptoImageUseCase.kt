package com.vero.cursowizelinecriptomonedas.domain

import android.util.Log
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoImageDaoMapper
import javax.inject.Inject

class GetCryptoImageUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    suspend operator fun invoke(crypto: String): ApiResponseStatus<String?>{
        val cryptoImage = cryptoOrderRepository.getCryptoImgFromApi(crypto)
        return if(cryptoImage is ApiResponseStatus.Success){
            Log.i("okhttp", "Entra por Api")
            cryptoOrderRepository.clearCryptoImage(crypto)
            val cryptoImageEntity = cryptoImage.data?.let {
                CryptoImageDaoMapper().fromCryptoOrderDomainToEntity(crypto,it)
            }
            cryptoOrderRepository.insertCryptoImage(cryptoImageEntity)
            return cryptoImage
        }else{
            Log.i("okhttp", "Entra por DB")
            cryptoOrderRepository.getCryptoImgFromDataBase(crypto)
        }
    }
}