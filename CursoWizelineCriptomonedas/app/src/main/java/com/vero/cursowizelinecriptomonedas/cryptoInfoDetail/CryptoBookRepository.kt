package com.vero.cursowizelinecriptomonedas.cryptoInfoDetail

import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.model.CryptoBookDetail
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class CryptoBookRepository() {
    fun getDetailBook(crypto: String): Single<Response<CryptoBookDetail>> {
        return retrofitService.getDeatilCrypto(crypto)
    }
}