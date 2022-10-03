package com.vero.cursowizelinecriptomonedas.api

import android.os.Build
import com.vero.cursowizelinecriptomonedas.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class UserAgentInterceptor() : Interceptor {
    companion object {
        private const val USER_AGENT = "User-Agent"
    }

    private val userAgent: String =
        "${BuildConfig.VERSION_NAME} " +
        "build:${BuildConfig.VERSION_CODE} " +
        "Android SDK ${Build.VERSION.SDK_INT}) " +
        "Android Device ${Build.DEVICE}"


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestWithUserAgent = request.newBuilder().header(USER_AGENT, userAgent).build()

        return chain.proceed(requestWithUserAgent)
    }
}