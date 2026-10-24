
package com.deritzstore.app.domain.model

data class Payment(
    val id: Int,
    val saleId: Int,
    val amount: Double,
    val paymentDate: String,
    val paymentType: PaymentType,
    val paymentMethod: PaymentMethod,
    val receiptUri: String? = null,
    val notes: String = ""
)

enum class PaymentType {
    FIRST_PAYMENT,
    SECOND_PAYMENT,
    ADDITIONAL_PAYMENT
}

enum class PaymentMethod {
    CASH,
    SINPE_MOVIL,
    BANK_TRANSFER
}
