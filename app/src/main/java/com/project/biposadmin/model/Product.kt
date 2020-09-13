package com.project.biposadmin.model

data class Product(
    val id: String? = "",
    val image: String? = "",
    val name: String? = "",
    val brand: String? = "",
    val price: Int? = 0,
    val phoneNumber: String? = "",
    val description: String? = "",
    val type: String? = ""
)