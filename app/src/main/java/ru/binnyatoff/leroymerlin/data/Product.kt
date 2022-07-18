package ru.binnyatoff.leroymerlin.data

data class Product(
    val productId: Int,
    val itemWeight: Double,
    val inBag: Boolean,
    val inShopList: Boolean,
    val productName: String,
    val productPrice: String,
    val twoProductPrice: String,
    val productImage:Int
)