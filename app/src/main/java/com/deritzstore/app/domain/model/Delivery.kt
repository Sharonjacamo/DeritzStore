
package com.deritzstore.app.domain.model

data class Delivery(
    val id: Int,
    val saleId: Int,
    val clientId: Int,
    val address: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val scheduledDate: String,
    val deliveredDate: String? = null,
    val status: DeliveryStatus = DeliveryStatus.PENDING,
    val notes: String = ""
)

enum class DeliveryStatus {
    PENDING,
    SCHEDULED,
    IN_TRANSIT,
    DELIVERED,
    CANCELLED
}
