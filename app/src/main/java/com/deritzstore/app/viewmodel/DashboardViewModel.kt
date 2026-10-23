
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Sale
import com.deritzstore.app.domain.model.SaleStatus
import com.deritzstore.app.domain.model.SaleType
import com.deritzstore.app.domain.repository.IStoreRepository

class DashboardViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun getPendingSalesCount(): Int {
        return repository.getSales().count {
            it.status == SaleStatus.PENDING
        }
    }

    fun getOverduePaymentsCount(): Int {
        return repository.getSales().count {
            it.status == SaleStatus.OVERDUE &&
                    it.remainingBalance > 0.0
        }
    }

    fun getPaidSalesCount(): Int {
        return repository.getSales().count {
            it.status == SaleStatus.PAID
        }
    }

    fun getWholesaleOrdersCount(): Int {
        return repository.getSales().count {
            it.saleType == SaleType.WHOLESALE
        }
    }

    fun getRecentSales(limit: Int = 5): List<Sale> {
        return repository.getSales()
            .sortedByDescending { it.saleDate }
            .take(limit)
    }

    fun getTotalSalesAmount(): Double {
        return repository.getSales().sumOf {
            it.totalAmount
        }
    }

    fun getTotalPendingBalance(): Double {
        return repository.getSales().sumOf {
            it.remainingBalance.coerceAtLeast(0.0)
        }
    }
}
