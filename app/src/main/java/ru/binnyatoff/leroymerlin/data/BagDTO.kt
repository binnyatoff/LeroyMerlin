package ru.binnyatoff.leroymerlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Bag")
data class BagDTO(
    @PrimaryKey val productId:Int,
    val itemWeight: Double,
    val inBag: Boolean,
    val inShopList: Boolean,
    val productName: String,
    val productPrice: String,
    val twoProductPrice: String,
    val productImage:Int
)