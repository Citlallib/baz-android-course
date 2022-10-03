package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoList.CryptoRepository
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCryptoListUseCaseTest {

    @RelaxedMockK
    private lateinit var cryptoRepository: CryptoRepository

    lateinit var getCryptoListUseCase: GetCryptoListUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCryptoListUseCase = GetCryptoListUseCase(cryptoRepository)
    }

    @Test
    fun `When the API return anything then get values from de Data base`() = runBlocking {
        //GIVEN
        val listApiResponseStatus: ApiResponseStatus<List<Crypto>> = ApiResponseStatus.Error(204)
        coEvery { cryptoRepository.downloadCrypto() } returns listApiResponseStatus
        //WHEN
        getCryptoListUseCase()
        //THEN
        coVerify(exactly = 1) { cryptoRepository.downloadCryptoFromDataBase() }
    }

    @Test
    fun `When the API return something then get value from api`() = runBlocking {
        //GIVEN
        val list: List<Crypto> = listOf(
            Crypto(
                book = "btc_mxn",
                minimum_price = "381040",
                maximum_price = "389880",
                minimum_value = "381040",
                maximum_value = "389880",
                minimum_amount = "381040",
                maximum_amount = "389880",
                tick_size = "10"
            )
        )
        val listApiResponseStatus: ApiResponseStatus<List<Crypto>> = ApiResponseStatus.Success(list)
        coEvery { cryptoRepository.downloadCrypto() } returns listApiResponseStatus
        //WHEN
        val response = getCryptoListUseCase()
        //THEN
        coVerify(exactly = 0) { cryptoRepository.downloadCryptoFromDataBase() }
        coVerify(exactly = 1) { cryptoRepository.clearCrypto() }
        coVerify(exactly = 1) { cryptoRepository.insertCrypto(any()) }
        assert(listApiResponseStatus == response)
    }
}