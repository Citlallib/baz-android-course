package com.vero.cursowizelinecriptomonedas.cryptoList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import com.vero.cursowizelinecriptomonedas.domain.GetCryptoListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CryptoListViewModelTest {

    @RelaxedMockK
    private lateinit var getCryptoListUseCase: GetCryptoListUseCase

    private lateinit var cryptoListViewModel: CryptoListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cryptoListViewModel = CryptoListViewModel(getCryptoListUseCase)
        Dispatchers.setMain(Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when download crypto`() = runTest {
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
        coEvery { getCryptoListUseCase() } returns listApiResponseStatus
        //WHEN
        cryptoListViewModel
        //THEN
        assert(cryptoListViewModel.cryptoList.value == listApiResponseStatus)

    }
}