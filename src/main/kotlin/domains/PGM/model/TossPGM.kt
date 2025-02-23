package org.economic.statistics.domains.PGM.types

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val amount: Int,
    val orderId: String,
    val paymentKey: String,

    val orderName: String? = null,
    val customerEmail: String? = null,
    val customerName: String? = null
)

@Serializable
data class PayRequest(
    val amount: Int,
    val orderId: String,
    val orderName: String? = null,
)


@Serializable
data class ConfirmBody(
    val orderId : String,
    val amount: Int,
    val paymentKey: String
)