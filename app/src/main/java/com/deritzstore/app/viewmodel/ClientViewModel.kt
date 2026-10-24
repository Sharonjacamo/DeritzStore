
package com.deritzstore.app.viewmodel

import androidx.lifecycle.ViewModel
import com.deritzstore.app.domain.model.Client
import com.deritzstore.app.domain.repository.IStoreRepository

class ClientViewModel(
    private val repository: IStoreRepository
) : ViewModel() {

    fun addClient(client: Client) {
        repository.addClient(client)
    }

    fun getClients(): List<Client> {
        return repository.getClients()
    }

    fun getClientById(id: Int): Client? {
        return repository.getClientById(id)
    }

    fun updateClient(client: Client) {
        repository.updateClient(client)
    }

    fun deleteClient(id: Int) {
        repository.deleteClient(id)
    }

    fun searchClients(query: String): List<Client> {
        return repository.getClients().filter {
            it.fullName.contains(query, ignoreCase = true)
        }
    }
}
