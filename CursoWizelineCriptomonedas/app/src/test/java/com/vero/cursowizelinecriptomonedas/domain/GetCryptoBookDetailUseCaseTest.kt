package com.vero.cursowizelinecriptomonedas.domain

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderRepository
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCryptoBookDetailUseCaseTest{
    @RelaxedMockK
    private lateinit var cryptoOrderRepository: CryptoOrderRepository

    lateinit var getCryptoBookDetailUseCase: GetCryptoBookDetailUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCryptoBookDetailUseCase = GetCryptoBookDetailUseCase(cryptoOrderRepository)
    }

    @Test
    fun `When the API return anything then get values from de Data base`() = runBlocking {
        //GIVEN
        val listApiResponseStatus: ApiResponseStatus<CryptoBookDetailPayload?> = ApiResponseStatus.Error(204)
        coEvery { cryptoOrderRepository.getCryptoBookDetailFromApi(any()) } returns listApiResponseStatus
        //WHEN
       getCryptoBookDetailUseCase("btc_mxn")
        //THEN
        coVerify(exactly = 1) { cryptoOrderRepository.getCryptoBookDetailFromDataBase(any()) }
    }

    @Test
    fun `When the API return something then get value from api`() = runBlocking {
        //GIVEN
        val cryptoBookDetailPayload: CryptoBookDetailPayload = CryptoBookDetailPayload("ask","bid","btc_mxn", "change_24","03/10/2022", "40000","40000", "39000", "10", "vap")
        val listApiResponseStatus: ApiResponseStatus<CryptoBookDetailPayload?> = ApiResponseStatus.Success(cryptoBookDetailPayload)
        coEvery { cryptoOrderRepository.getCryptoBookDetailFromApi(any()) } returns listApiResponseStatus
        //WHEN
        val response = getCryptoBookDetailUseCase("btc_mxn")
        //THEN
        coVerify(exactly = 0) { cryptoOrderRepository.getCryptoBookDetailFromDataBase(any()) }
        coVerify(exactly = 1) { cryptoOrderRepository.clearCryptoBookDetail(any())}
        coVerify(exactly = 1) { cryptoOrderRepository.insertCryptoBookDetail(any()) }
        assert(listApiResponseStatus == response)
    }
}