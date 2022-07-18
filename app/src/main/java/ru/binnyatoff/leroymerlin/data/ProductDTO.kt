package ru.binnyatoff.leroymerlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class ProductDTO(
    @PrimaryKey (autoGenerate = true) val productId:Int = 0,
    val itemWeight: Double,
    val inBag: Boolean = false,
    val inShopList: Boolean = false,
    val productName: String,
    val productPrice: String,
    val twoProductPrice: String,
    val productImage:Int
)
