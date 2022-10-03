package com.vero.cursowizelinecriptomonedas.data.model

import com.vero.cursowizelinecriptomonedas.api.dto.CryptoOrderDTO

data class CryptoOrderPayload(
    val asks:       List<CryptoOrderDTO>,
    val bids:       List<CryptoOrderDTO>,
    val sequence:   String,
    val updated_at: String
)