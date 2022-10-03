package com.vero.cursowizelinecriptomonedas.domain


import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
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
    fun `test prueba`(){
        assert(2+2==4)
    }

    /**@Test
    fun `When the API return anything then get values from de Data base`() = runBlocking {
        //GIVEN
        val lista: List<Crypto> = listOf()
        coEvery { cryptoRepository.downloadCrypto() } returns makeNetworkCall { lista }
        //WHEN
        getCryptoListUseCase()
        //THEN
        coVerify(exactly = 1) { cryptoRepository.downloadCryptoFromDataBase() }
    }*/

    /**@Test
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
        coEvery { cryptoRepository.downloadCrypto() } returns makeNetworkCall { list }
        //WHEN
        val response = getCryptoListUseCase()
        //THEN
        coVerify(exactly = 1) { cryptoRepository.clearCrypto() }
        coVerify { cryptoRepository.insertCrypto(any()) }
        coVerify(exactly = 0) { cryptoRepository.downloadCryptoFromDataBase() }
        assert(list == response)
    }*/
}