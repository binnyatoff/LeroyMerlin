package ru.binnyatoff.leroymerlin.data

import androidx.room.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getAll(): List<ProductDTO>

    @Insert
    fun insertAll(product: List<ProductDTO>)

    @Query("SELECT * FROM Product WHERE productId = :productId")
    fun get(productId: Int): Product

    @Query("UPDATE Product SET inBag= :inBag WHERE productId = :productId")
    fun updateInBag(productId: Int, inBag: Boolean)


    @Query("UPDATE Product SET InShopList= :InShopList WHERE productId = :productId")
    fun updateInShopList(productId: Int, InShopList: Boolean)
}

