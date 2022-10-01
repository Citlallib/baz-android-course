package com.vero.cursowizelinecriptomonedas.domain

import android.util.Log
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoBookDetailDaoMapper
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload
import javax.inject.Inject

class GetCryptoBookDetailUseCase @Inject constructor(
    private val cryptoOrderRepository: CryptoOrderRepository
) {
    suspend operator fun invoke(crypto: String): CryptoBookDetailPayload? {
         val cryptoOrderDetailPayload = cryptoOrderRepository.getCryptoBookDetailFromApi(crypto)

        return if (cryptoOrderDetailPayload != null){
            Log.i("okhttp", "Entra por Api")
            cryptoOrderRepository.clearCryptoBookDetail()
            val cryptoOrder = CryptoBookDetailDaoMapper().fromCryptoOrderDomainToEntity(cryptoOrderDetailPayload.payload)
            cryptoOrderRepository.insertCryptoBookDetail(cryptoOrder)
            return cryptoOrderDetailPayload.payload
        }else {
            Log.i("okhttp", "Entra por DB")
            cryptoOrderRepository.getCryptoBookDetailFromDataBase(crypto)
        }
    }
}