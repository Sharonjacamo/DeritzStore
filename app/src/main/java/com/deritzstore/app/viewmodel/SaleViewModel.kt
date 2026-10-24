
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Sale
import com.deritzstore.app.domain.model.SaleStatus
import com.deritzstore.app.domain.model.SaleType
import com.deritzstore.app.domain.repository.IStoreRepository

class SaleViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun addSale(sale: Sale) {
        repository.addSale(sale)
    }

    fun getSales(): List<Sale> {
        return repository.getSales()
    }

    fun getSaleById(id: Int): Sale? {
        return repository.getSaleById(id)
    }

    fun updateSale(sale: Sale) {
        repository.updateSale(sale)
    }

    fun deleteSale(id: Int) {
        repository.deleteSale(id)
    }

    fun getSalesByStatus(status: SaleStatus): List<Sale> {
        return repository.getSales().filter {
            it.status == status
        }
    }

    fun getSalesByType(type: SaleType): List<Sale> {
        return repository.getSales().filter {
            it.saleType == type
        }
    }

    fun getSalesByClient(clientId: Int): List<Sale> {
        return repository.getSales().filter {
            it.clientId == clientId
        }
    }

    fun getPendingSales(): List<Sale> {
        return getSalesByStatus(SaleStatus.PENDING)
    }

    fun getPaidSales(): List<Sale> {
        return getSalesByStatus(SaleStatus.PAID)
    }

    fun getOverdueSales(): List<Sale> {
        return getSalesByStatus(SaleStatus.OVERDUE)
    }

    fun getWholesaleSales(): List<Sale> {
        return getSalesByType(SaleType.WHOLESALE)
    }

    fun getTotalSalesAmount(): Double {
        return repository.getSales().sumOf {
            it.totalAmount
        }
    }
}
