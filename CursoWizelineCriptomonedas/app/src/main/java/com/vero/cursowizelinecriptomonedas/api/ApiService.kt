package com.vero.cursowizelinecriptomonedas.api

import com.vero.cursowizelinecriptomonedas.api.response.CryptoListApiResponse
import com.vero.cursowizelinecriptomonedas.api.response.CryptoOrderListApiResponse
import com.vero.cursowizelinecriptomonedas.model.CryptoBookDetail
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val loggingInterceptor: HttpLoggingInterceptor
    get() = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

private val okHttpClient = OkHttpClient
    .Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

interface ApiService {
    @GET(BOOKS)
    suspend fun getAllCrypto(): CryptoListApiResponse

    @GET(ORDER_BOOK)
    suspend fun getOrderCrypto(@Query("book") book: String): CryptoOrderListApiResponse

    @GET(DETAIL_BOOK)
    fun getDeatilCrypto(@Query("book") book: String): Single<Response<CryptoBookDetail>>
}

//Use Service
object CryptoApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}