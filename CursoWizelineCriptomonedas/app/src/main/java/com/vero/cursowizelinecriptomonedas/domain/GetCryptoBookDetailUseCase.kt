package com.vero.cursowizelinecriptomonedas.domain

import android.util.Log
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoBookDetailDaoMapper
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetail
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload
import javax.inject.Inject

class GetCryptoBookDetailUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    suspend operator fun invoke(crypto: String): ApiResponseStatus<CryptoBookDetailPayload?> {
         val cryptoOrderDetailPayload = cryptoOrderRepository.getCryptoBookDetailFromApi(crypto)

        return if (cryptoOrderDetailPayload is ApiResponseStatus.Success){
            cryptoOrderRepository.clearCryptoBookDetail(crypto)
            val cryptoOrder = cryptoOrderDetailPayload.data?.let {
                CryptoBookDetailDaoMapper().fromCryptoOrderDomainToEntity(
                    it
                )
            }
            cryptoOrderRepository.insertCryptoBookDetail(cryptoOrder)
            return cryptoOrderDetailPayload
        }else {
            cryptoOrderRepository.getCryptoBookDetailFromDataBase(crypto)
        }
    }
}