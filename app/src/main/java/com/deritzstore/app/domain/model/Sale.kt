
package com.deritzstore.app.domain.model

data class Sale(
    val id: Int,
    val clientId: Int,
    val productId: Int,
    val quantity: Int,
    val saleType: SaleType,
    val unitPrice: Double,
    val totalAmount: Double,
    val firstPayment: Double,
    val remainingBalance: Double,
    val saleDate: String,
    val deliveryDate: String,
    val dueDate: String,
    val status: SaleStatus,
    val isDelivered: Boolean = false,
    val notes: String = ""
)

enum class SaleType {
    RETAIL,
    WHOLESALE
}

enum class SaleStatus {
    PENDING,
    PAID,
    OVERDUE
}
