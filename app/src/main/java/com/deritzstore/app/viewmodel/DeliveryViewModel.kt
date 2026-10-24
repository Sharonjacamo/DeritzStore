
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Delivery
import com.deritzstore.app.domain.model.DeliveryStatus
import com.deritzstore.app.domain.repository.IStoreRepository

class DeliveryViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun addDelivery(delivery: Delivery) {
        repository.addDelivery(delivery)
    }

    fun getDeliveries(): List<Delivery> {
        return repository.getDeliveries()
    }

    fun getDeliveryById(id: Int): Delivery? {
        return repository.getDeliveryById(id)
    }

    fun updateDelivery(delivery: Delivery) {
        repository.updateDelivery(delivery)
    }

    fun deleteDelivery(id: Int) {
        repository.deleteDelivery(id)
    }

    fun getDeliveriesByStatus(
        status: DeliveryStatus
    ): List<Delivery> {
        return repository.getDeliveries().filter {
            it.status == status
        }
    }

    fun getPendingDeliveries(): List<Delivery> {
        return getDeliveriesByStatus(DeliveryStatus.PENDING)
    }

    fun getScheduledDeliveries(): List<Delivery> {
        return getDeliveriesByStatus(DeliveryStatus.SCHEDULED)
    }

    fun getCompletedDeliveries(): List<Delivery> {
        return getDeliveriesByStatus(DeliveryStatus.DELIVERED)
    }

    fun getDeliveriesWithLocation(): List<Delivery> {
        return repository.getDeliveries().filter {
            it.latitude != null && it.longitude != null
        }
    }
}
