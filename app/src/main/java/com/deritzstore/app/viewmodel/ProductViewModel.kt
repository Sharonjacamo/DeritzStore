
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Product
import com.deritzstore.app.domain.repository.IStoreRepository

class ProductViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun addProduct(product: Product) {
        repository.addProduct(product)
    }

    fun getProducts(): List<Product> {
        return repository.getProducts()
    }

    fun getProductById(id: Int): Product? {
        return repository.getProductById(id)
    }

    fun updateProduct(product: Product) {
        repository.updateProduct(product)
    }

    fun deleteProduct(id: Int) {
        repository.deleteProduct(id)
    }

    fun searchProducts(query: String): List<Product> {
        return repository.getProducts().filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.brand.contains(query, ignoreCase = true)
        }
    }

    fun getAvailableProducts(): List<Product> {
        return repository.getProducts().filter {
            it.stock > 0 && it.isAvailable
        }
    }

    fun getLowStockProducts(limit: Int = 5): List<Product> {
        return repository.getProducts().filter {
            it.stock <= limit && it.stock >= 0
        }
    }
}
