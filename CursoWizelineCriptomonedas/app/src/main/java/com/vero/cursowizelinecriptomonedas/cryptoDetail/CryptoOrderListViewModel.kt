package com.vero.cursowizelinecriptomonedas.cryptoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.data.model.CryptoBookDetailPayload
import com.vero.cursowizelinecriptomonedas.data.model.CryptoOrder
import com.vero.cursowizelinecriptomonedas.domain.GetCryptoBookDetailUseCase
import com.vero.cursowizelinecriptomonedas.domain.GetCryptoDetailUseCase
import com.vero.cursowizelinecriptomonedas.domain.GetCryptoImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoOrderListViewModel @Inject constructor(
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase,
    private val getCryptoBookDetailUseCase: GetCryptoBookDetailUseCase,
    private val getCryptoImageUseCase: GetCryptoImageUseCase
) : ViewModel() {
    private val _cryptoOrderList = MutableLiveData<List<CryptoOrder>>()
    val cryptoOrderList: LiveData<List<CryptoOrder>>
        get() = _cryptoOrderList

    //status -- Status serviceResponse
    private val _status = MutableLiveData<ApiResponseStatus<List<CryptoOrder>>>()
    val status: LiveData<ApiResponseStatus<List<CryptoOrder>>>
        get() = _status

    //BookDetail
    private val _bookDetail = MutableLiveData<CryptoBookDetailPayload>()
    val bookDetail: LiveData<CryptoBookDetailPayload>
        get() = _bookDetail


    fun downloadCryptoOrder(crypto: String) {
        viewModelScope.launch {
            //_status.value = ApiResponseStatus.Loading()
            _cryptoOrderList.value = getCryptoDetailUseCase.invoke(crypto)
        }
    }

    fun downloadCryptoImage(crypto: String): String = getCryptoImageUseCase.getCryptoImg(crypto)


    fun downloadCryptoBookDetail(crypto: String) {
        viewModelScope.launch {
            _bookDetail.value = getCryptoBookDetailUseCase.invoke(crypto)
        }
    }

}