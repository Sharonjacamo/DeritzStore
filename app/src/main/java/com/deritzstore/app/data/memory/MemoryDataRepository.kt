
package com.deritzstore.app.data.memory

import com.deritzstore.app.domain.model.Client
import com.deritzstore.app.domain.model.Product
import com.deritzstore.app.domain.model.Sale
import com.deritzstore.app.domain.model.Payment
import com.deritzstore.app.domain.model.Delivery
import com.deritzstore.app.domain.repository.IStoreRepository

class MemoryDataRepository : IStoreRepository {

    private val clients = mutableListOf<Client>()
    private val products = mutableListOf<Product>()
    private val sales = mutableListOf<Sale>()
    private val payments = mutableListOf<Payment>()
    private val deliveries = mutableListOf<Delivery>()

    // CLIENT CRUD

    override fun addClient(client: Client) {
        require(clients.none { it.id == client.id }) {
            "Client ID already exists"
        }
        clients.add(client)
    }

    override fun getClients(): List<Client> = clients.toList()

    override fun getClientById(id: Int): Client? =
        clients.find { it.id == id }

    override fun updateClient(client: Client) {
        val index = clients.indexOfFirst { it.id == client.id }
        if (index != -1) {
            clients[index] = client
        }
    }

    override fun deleteClient(id: Int) {
        clients.removeAll { it.id == id }
    }

    // PRODUCT CRUD

    override fun addProduct(product: Product) {
        require(products.none { it.id == product.id }) {
            "Product ID already exists"
        }
        products.add(product)
    }

    override fun getProducts(): List<Product> = products.toList()

    override fun getProductById(id: Int): Product? =
        products.find { it.id == id }

    override fun updateProduct(product: Product) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            products[index] = product
        }
    }

    override fun deleteProduct(id: Int) {
        products.removeAll { it.id == id }
    }

    // SALE CRUD

    override fun addSale(sale: Sale) {
        require(sales.none { it.id == sale.id }) {
            "Sale ID already exists"
        }
        sales.add(sale)
    }

    override fun getSales(): List<Sale> = sales.toList()

    override fun getSaleById(id: Int): Sale? =
        sales.find { it.id == id }

    override fun updateSale(sale: Sale) {
        val index = sales.indexOfFirst { it.id == sale.id }
        if (index != -1) {
            sales[index] = sale
        }
    }

    override fun deleteSale(id: Int) {
        sales.removeAll { it.id == id }
    }

    // PAYMENT CRUD

    override fun addPayment(payment: Payment) {
        require(payments.none { it.id == payment.id }) {
            "Payment ID already exists"
        }
        payments.add(payment)
    }

    override fun getPayments(): List<Payment> = payments.toList()

    override fun getPaymentsBySale(saleId: Int): List<Payment> =
        payments.filter { it.saleId == saleId }

    override fun updatePayment(payment: Payment) {
        val index = payments.indexOfFirst { it.id == payment.id }
        if (index != -1) {
            payments[index] = payment
        }
    }

    override fun deletePayment(id: Int) {
        payments.removeAll { it.id == id }
    }

    // DELIVERY CRUD

    override fun addDelivery(delivery: Delivery) {
        require(deliveries.none { it.id == delivery.id }) {
            "Delivery ID already exists"
        }
        deliveries.add(delivery)
    }

    override fun getDeliveries(): List<Delivery> =
        deliveries.toList()

    override fun getDeliveryById(id: Int): Delivery? =
        deliveries.find { it.id == id }

    override fun updateDelivery(delivery: Delivery) {
        val index = deliveries.indexOfFirst { it.id == delivery.id }
        if (index != -1) {
            deliveries[index] = delivery
        }
    }

    override fun deleteDelivery(id: Int) {
        deliveries.removeAll { it.id == id }
    }
}
