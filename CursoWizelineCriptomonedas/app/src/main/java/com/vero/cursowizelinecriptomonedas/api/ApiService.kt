package com.vero.cursowizelinecriptomonedas.api

import com.vero.cursowizelinecriptomonedas.api.response.CryptoListApiResponse
import com.vero.cursowizelinecriptomonedas.api.response.CryptoOrderListApiResponse
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    .addInterceptor(UserAgentInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

interface ApiService {
    @GET(BOOKS)
    suspend fun getAllCrypto(): CryptoListApiResponse

    @GET(ORDER_BOOK)
    suspend fun getOrderCrypto(@Query("book") book: String): CryptoOrderListApiResponse?

    @GET(DETAIL_BOOK)
    suspend fun getDeatilCrypto(@Query("book") book: String): CryptoBookDetail?
}

//Use Service
object CryptoApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}