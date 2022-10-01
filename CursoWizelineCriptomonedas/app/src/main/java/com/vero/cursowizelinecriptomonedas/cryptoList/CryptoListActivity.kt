package com.vero.cursowizelinecriptomonedas.cryptoList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderDetailActivity
import com.vero.cursowizelinecriptomonedas.cryptoDetail.CryptoOrderDetailActivity.Companion.CRYPTO_KEY
import com.vero.cursowizelinecriptomonedas.databinding.ActivityCryptoListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoListActivity : AppCompatActivity() {
    //Instance del ViewModel
    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel

        val recycler = binding.cryptoRecycler
        recycler.layoutManager = LinearLayoutManager(this)

        val adapter = CryptoAdapter()

        adapter.setOnItemClickListener { crypto ->
            //Put extra Crypto to CryptoDetailActivity
            val intent = Intent(this, CryptoOrderDetailActivity::class.java)
            intent.putExtra(CRYPTO_KEY, crypto)
            startActivity(intent)
        }

        recycler.adapter = adapter

        //Observer
        cryptoListViewModel.cryptoList.observe(this) { cryptoList ->
            adapter.submitList(cryptoList)
        }
        //Observe Status
        cryptoListViewModel.status.observe(this) { status ->
            when (status) {
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    showErrorDialog(status.messageId)
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }
        }
    }

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