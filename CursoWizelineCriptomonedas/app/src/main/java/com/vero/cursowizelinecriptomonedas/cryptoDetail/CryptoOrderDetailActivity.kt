package com.vero.cursowizelinecriptomonedas.cryptoDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.vero.cursowizelinecriptomonedas.data.model.Crypto
import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoDetailBinding
import com.vero.cursowizelinecriptomonedas.data.model.CryptoOrder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoOrderDetailActivity : AppCompatActivity() {
    companion object {
        //Key
        const val CRYPTO_KEY = "crypto"
    }

    private val cryptoOrderListViewModel: CryptoOrderListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crypto = intent?.extras?.getParcelable<Crypto>(CRYPTO_KEY)

        if (crypto == null) {
            Toast.makeText(this, R.string.error_showing_crypto_not_found, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.cryptoBook.text = crypto.book
        binding.minimumValue.text = crypto.minimum_value
        binding.maximumValue.text = crypto.maximum_value
        /*binding.cryptoImage.load(cryptoImage(crypto.book)) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }*/
        binding.crypto = crypto
        binding.closeButton.setOnClickListener {
            finish()
        }

        cryptoInit(crypto.book)

        /***Recycler***/
        val loadingWheel = binding.loadingWheel
        val recycler = binding.cryptoOrderRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = CryptoOrderAdapter()

        recycler.adapter = adapter
        //Observer
        cryptoOrderListViewModel.cryptoOrderList.observe(this) { cryptoOrderList: List<CryptoOrder> ->
            adapter.submitList(cryptoOrderList)
        }
        cryptoOrderListViewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
        cryptoOrderListViewModel.bookDetail.observe(this) { cryptoBookDetail ->
            binding.minimumPrice.text = cryptoBookDetail.low
            binding.maximumPrice.text = cryptoBookDetail.high
            binding.lastPrice.text = cryptoBookDetail.last
        }
    }

    private fun cryptoInit(crypto: String) {
        cryptoOrderListViewModel.downloadCryptoOrder(crypto)
        cryptoOrderListViewModel.downloadCryptoBookDetail(crypto)
    }

   // private fun cryptoImage(crypto: String): String = cryptoOrderListViewModel.downloadCryptoImage(crypto)

    private fun showErrorDialog(message: Int){
        AlertDialog.Builder(this)
            .setTitle(R.string.error_unknown)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok){_,_ ->
                //Dismisss dialog
            }
            .create()
            .show()
    }
}