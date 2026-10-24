package com.deritzstore.app.domain.model

data class Client(
    val id: Int,
    val fullName: String,
    val phone: String,
    val email: String = "",
    val address: String,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val notes: String = "",
    val isActive: Boolean = true
)
