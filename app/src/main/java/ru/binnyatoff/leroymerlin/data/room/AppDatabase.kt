package ru.binnyatoff.leroymerlin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductDTO::class, BagDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun bagDao(): BagDao
}