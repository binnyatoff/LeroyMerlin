package ru.binnyatoff.leroymerlin.data.room

import androidx.room.*

@Dao
interface BagDao {
    @Query("SELECT * FROM Bag")
    fun getAll(): List<BagDTO>

    @Insert
    fun insert(product: BagDTO)

    @Delete
    fun delete(product: BagDTO)

    @Query("UPDATE Bag SET inBag= :inBag WHERE productId = :productId")
    fun updateInBag(productId: Int, inBag: Boolean)
}