package com.vero.cursowizelinecriptomonedas.cryptoList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.domain.GetCryptoListUseCase
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase
): ViewModel() {

    private val _cryptoList = MutableLiveData<List<Crypto>>()
    val cryptoList: LiveData<List<Crypto>>
        get() = _cryptoList

    private val _status = MutableLiveData<ApiResponseStatus<List<Crypto>>>()
    val status: LiveData<ApiResponseStatus<List<Crypto>>>
        get() = _status

    init {
        downloadCrypto()
    }

    private fun downloadCrypto() {
        //Coroutine
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getCryptoListUseCase.invoke())
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Crypto>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            Log.i("okhttp", "Es success")
            if (apiResponseStatus.data.isNotEmpty()){
                Log.i("okhttp", "No esta vacias")
                _cryptoList.value = apiResponseStatus.data
            }
            else{
                Log.i("okhttp", "Esta vacias")
                _status.value = ApiResponseStatus.Error(R.string.sorry_try_later)
            }
        }
        _status.value = apiResponseStatus
    }
}