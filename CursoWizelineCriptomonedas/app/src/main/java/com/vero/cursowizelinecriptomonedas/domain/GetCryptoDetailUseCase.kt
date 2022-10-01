package com.vero.cursowizelinecriptomonedas.domain

import android.util.Log
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoOrderDaoMapper
import com.vero.cursowizelinecriptomonedas.data.model.CryptoOrder
import javax.inject.Inject

class GetCryptoDetailUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    suspend operator fun invoke(crypto: String): ApiResponseStatus<List<CryptoOrder>> {
        val cryptoOrderList = cryptoOrderRepository.getCryptoOrderFromApi(crypto)
        return if (cryptoOrderList is ApiResponseStatus.Success){
            Log.i("okhttp", "Entra por Api")
            cryptoOrderRepository.clearCryptoOrder()
            val cryptoOrderEntity = CryptoOrderDaoMapper().fromCryptoOrderListToCryptoOrderEntity(
                cryptoOrderList.data
            )
            cryptoOrderRepository.insertCryptoOrder(cryptoOrderEntity)
            return cryptoOrderList
        }else{
            Log.i("okhttp", "Entra por DB")
            cryptoOrderRepository.getCryptoOrderFromDataBase(crypto)
        }
    }
}