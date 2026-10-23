
package com.deritzstore.app.domain.model

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val size: Int,
    val color: String,
    val retailPrice: Double,
    val wholesalePrice: Double,
    val stock: Int,
    val imageUri: String? = null,
    val description: String = "",
    val isAvailable: Boolean = true
)
