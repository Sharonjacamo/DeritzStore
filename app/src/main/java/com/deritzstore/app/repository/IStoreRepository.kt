
package com.deritzstore.app.domain.repository

import com.deritzstore.app.domain.model.Client
import com.deritzstore.app.domain.model.Product
import com.deritzstore.app.domain.model.Sale
import com.deritzstore.app.domain.model.Payment
import com.deritzstore.app.domain.model.Delivery

interface IStoreRepository {

    // Client CRUD
    fun addClient(client: Client)
    fun getClients(): List<Client>
    fun getClientById(id: Int): Client?
    fun updateClient(client: Client)
    fun deleteClient(id: Int)

    // Product CRUD
    fun addProduct(product: Product)
    fun getProducts(): List<Product>
    fun getProductById(id: Int): Product?
    fun updateProduct(product: Product)
    fun deleteProduct(id: Int)

    // Sale CRUD
    fun addSale(sale: Sale)
    fun getSales(): List<Sale>
    fun getSaleById(id: Int): Sale?
    fun updateSale(sale: Sale)
    fun deleteSale(id: Int)

    // Payment CRUD
    fun addPayment(payment: Payment)
    fun getPayments(): List<Payment>
    fun getPaymentsBySale(saleId: Int): List<Payment>
    fun updatePayment(payment: Payment)
    fun deletePayment(id: Int)

    // Delivery CRUD
    fun addDelivery(delivery: Delivery)
    fun getDeliveries(): List<Delivery>
    fun getDeliveryById(id: Int): Delivery?
    fun updateDelivery(delivery: Delivery)
    fun deleteDelivery(id: Int)
}
