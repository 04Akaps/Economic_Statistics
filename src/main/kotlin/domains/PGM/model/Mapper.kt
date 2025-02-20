package org.economic.statistics.domains.PGM.model

import org.economic.statistics.custom.interfaces.Mapper
import org.economic.statistics.domains.PGM.types.PaymentRequest
import org.economic.statistics.domains.PGM.types.ConfirmBody

object ConfirmRequestMapper : Mapper<PaymentRequest, ConfirmBody> {
    override fun ToAnotherType(input: PaymentRequest): ConfirmBody {
        return ConfirmBody(orderId = input.orderId, amount = input.amount, paymentKey = input.paymentKey)
    }
}