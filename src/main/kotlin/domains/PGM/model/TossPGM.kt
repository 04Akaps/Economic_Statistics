package org.economic.statistics.domains.PGM.types

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val amount: Int,
    val orderId: String,
    val orderName: String,
    val customerEmail: String,
    val customerName: String,
    val paymentKey: String
)