package ru.binnyatoff.leroymerlin.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.binnyatoff.leroymerlin.data.room.AppDatabase
import ru.binnyatoff.leroymerlin.data.room.BagDao
import ru.binnyatoff.leroymerlin.data.room.ProductDao
import ru.binnyatoff.leroymerlin.repository.ProductListCache
import ru.binnyatoff.leroymerlin.repository.Repository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "leroymerlin_db"
        ).build()
    }

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
       return appDatabase.productDao()
    }

    @Provides
    fun provideBagDao(appDatabase: AppDatabase): BagDao {
        return appDatabase.bagDao()
    }

    @Provides
    fun provideProductListCache(): ProductListCache {
        return ProductListCache()
    }

    @Provides
    @Singleton
    fun provideRepository(productDao: ProductDao, bagDao: BagDao, productListCache: ProductListCache):Repository{
        return Repository(productDao, bagDao, productListCache)
    }

}