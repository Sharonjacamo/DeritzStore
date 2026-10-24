
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Payment
import com.deritzstore.app.domain.repository.IStoreRepository

class PaymentViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun addPayment(payment: Payment) {
        repository.addPayment(payment)
    }

    fun getPayments(): List<Payment> {
        return repository.getPayments()
    }

    fun getPaymentsBySale(saleId: Int): List<Payment> {
        return repository.getPaymentsBySale(saleId)
    }

    fun updatePayment(payment: Payment) {
        repository.updatePayment(payment)
    }

    fun deletePayment(id: Int) {
        repository.deletePayment(id)
    }

    fun getTotalPaid(saleId: Int): Double {
        return repository.getPaymentsBySale(saleId)
            .sumOf { it.amount }
    }

    fun getRemainingBalance(saleId: Int): Double {
        val sale = repository.getSaleById(saleId)
            ?: return 0.0

        val totalPaid = getTotalPaid(saleId)

        return (sale.totalAmount - totalPaid).coerceAtLeast(0.0)
    }

    fun isSaleFullyPaid(saleId: Int): Boolean {
        val sale = repository.getSaleById(saleId)
            ?: return false

        return getTotalPaid(saleId) >= sale.totalAmount
    }
}
