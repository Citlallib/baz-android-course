package com.vero.cursowizelinecriptomonedas.cryptoDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.R
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

    private val _status = MutableLiveData<ApiResponseStatus<List<CryptoOrder>?>>()
    val status: LiveData<ApiResponseStatus<List<CryptoOrder>?>>
        get() = _status

    private val _bookDetail = MutableLiveData<CryptoBookDetailPayload>()
    val bookDetail: LiveData<CryptoBookDetailPayload>
        get() = _bookDetail

    private val _statusBookDetail = MutableLiveData<ApiResponseStatus<CryptoBookDetailPayload?>>()
    val statusBookDetail: LiveData<ApiResponseStatus<CryptoBookDetailPayload?>>
        get() = _statusBookDetail

    fun downloadCryptoOrder(crypto: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getCryptoDetailUseCase.invoke(crypto))
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<CryptoOrder>?>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            Log.i("okhttp", "Es success")
            if (apiResponseStatus.data?.isNotEmpty() == true){
                Log.i("okhttp", "No esta vacias")
                Log.i("okhttp", "${apiResponseStatus.data}")
                _cryptoOrderList.value = apiResponseStatus.data
            }
            else{
                Log.i("okhttp", "Esta vacias")
                _status.value = ApiResponseStatus.Error(R.string.sorry_try_later)
            }
        }
        _status.value = apiResponseStatus
    }

    //fun downloadCryptoImage(crypto: String): String = getCryptoImageUseCase.getCryptoImg(crypto)


    fun downloadCryptoBookDetail(crypto: String) {
        viewModelScope.launch {
            _statusBookDetail.value = ApiResponseStatus.Loading()
            handleResponseStatusBookDetail(getCryptoBookDetailUseCase.invoke(crypto))
        }
    }
    private fun handleResponseStatusBookDetail(apiResponseStatus: ApiResponseStatus<CryptoBookDetailPayload?>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            Log.i("okhttp", "Es success")
            if (apiResponseStatus.data != null){
                Log.i("okhttp", "No esta vacias")
                _bookDetail.value = apiResponseStatus.data
            }
            else{
                Log.i("okhttp", "Esta vacias")
                _statusBookDetail.value = ApiResponseStatus.Error(R.string.sorry_try_later)
            }
        }
        _statusBookDetail.value = apiResponseStatus
    }

}