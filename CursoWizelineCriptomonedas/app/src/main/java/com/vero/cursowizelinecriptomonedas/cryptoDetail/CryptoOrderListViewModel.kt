package com.vero.cursowizelinecriptomonedas.cryptoDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoInfoDetail.CryptoBookRepository
import com.vero.cursowizelinecriptomonedas.model.CryptoBookDetail
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class CryptoOrderListViewModel : ViewModel() {
    private val _cryptoOrderList = MutableLiveData<List<CryptoOrder>>()

    val cryptoOrderList: LiveData<List<CryptoOrder>>
        get() = _cryptoOrderList

    //status -- Status serviceResponse
    private val _status = MutableLiveData<ApiResponseStatus<List<CryptoOrder>>>()
    val status: LiveData<ApiResponseStatus<List<CryptoOrder>>>
        get() = _status

    //BookDetail
    private val _bookDetail = MutableLiveData<CryptoBookDetail>()
    val bookDetail: LiveData<CryptoBookDetail>
        get() = _bookDetail

    private val cryptoOrderRepository = CryptoOrderRepository()
    private val cryptoBookRepository = CryptoBookRepository()

    fun downloadCryptoOrder(crypto: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(cryptoOrderRepository.downloadCryptoOrder(crypto))
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<CryptoOrder>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _cryptoOrderList.value = apiResponseStatus.payload
        }
        _status.value = apiResponseStatus
    }

    fun downloadCryptoImage(crypto: String): String =
        "https://firebasestorage.googleapis.com/v0/b/crypto-d6420.appspot.com/o/cryptocurrency_icon%2Fic_crypto_${
            crypto.split("_").get(0)
        }.png?alt=media"

    fun downloadCryptoBookDetail(crypto: String) {
        cryptoBookRepository.getDetailBook(crypto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { onSuccess, onError ->
                onSuccess.let { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _bookDetail.value = it
                        }
                    }
                }
                onError.let {
                    
                }
            }
    }

}