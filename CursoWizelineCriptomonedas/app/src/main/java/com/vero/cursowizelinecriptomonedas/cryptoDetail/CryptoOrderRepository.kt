package com.vero.cursowizelinecriptomonedas.cryptoDetail

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoOrderDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoBookDetailDaoMapper
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoBookDetailDao
import com.vero.cursowizelinecriptomonedas.data.database.dao.CryptoOrderDao
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoBookDetailEntity
import com.vero.cursowizelinecriptomonedas.data.database.entities.CryptoOrderEntity
import com.vero.cursowizelinecriptomonedas.data.database.mapper.CryptoOrderDaoMapper
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetail
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload
import com.vero.cursowizelinecriptomonedas.data.model.CryptoOrder
import javax.inject.Inject

class CryptoOrderRepository @Inject constructor(
    private val cryptoBookDetailDao: CryptoBookDetailDao,
    private val cryptoOrderDao: CryptoOrderDao
) {
    /* Crypto Order List*/
    suspend fun getCryptoOrderFromApi(crypto: String): ApiResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoOrderApiResponse = retrofitService.getOrderCrypto(crypto)
            val cryptoOrderDTOList = cryptoOrderApiResponse.payload.asks
            val cryptoOrderDTOMapper = CryptoOrderDTOMapper()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderDTOList)
        }
    }

    suspend fun getCryptoOrderFromDataBase(crypto: String): ApiResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoListBDResponse = cryptoOrderDao.getCryptoOrder(crypto)
            val cryptoOrderDaoMapper = CryptoOrderDaoMapper()
            cryptoOrderDaoMapper.fromCryptoOrderListToCryptoOrderList(
                cryptoListBDResponse
            )
        }
    }

    suspend fun insertCryptoOrder(cryptoOrderList: List<CryptoOrderEntity>) {
        cryptoOrderDao.insertCryptoOrder(cryptoOrderList)
    }

    suspend fun clearCryptoOrder() {
        cryptoOrderDao.deleteCryptoOrder()
    }

    /* Crypto Book Detail */
    suspend fun getCryptoBookDetailFromApi(crypto: String): CryptoBookDetail? {
        return retrofitService.getDeatilCrypto(crypto)
    }

    suspend fun getCryptoBookDetailFromDataBase(crypto: String): CryptoBookDetailPayload? {
        val cryptoBookDetailResponse: CryptoBookDetailEntity? = cryptoBookDetailDao.getCryptoOrderByBook(crypto)
        val cryptoBookDetailMapper = CryptoBookDetailDaoMapper()
        return cryptoBookDetailResponse?.let {
            cryptoBookDetailMapper.fromCryptoOrderEntityToDetail(it)
        }
    }

    suspend fun insertCryptoBookDetail(crypto: CryptoBookDetailEntity){
        cryptoBookDetailDao.insert(crypto)
    }

    suspend fun clearCryptoBookDetail(){
        cryptoBookDetailDao.delete()
    }

    /* Crypto Image */
    fun getCryptoImg(crypto: String): String =
        "https://firebasestorage.googleapis.com/v0/b/crypto-d6420.appspot.com/o/cryptocurrency_icon%2Fic_crypto_${
            crypto.split("_").get(0)
        }.png?alt=media"
}