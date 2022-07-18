package ru.binnyatoff.leroymerlin.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.binnyatoff.leroymerlin.data.*
import ru.binnyatoff.leroymerlin.data.room.BagDTO
import ru.binnyatoff.leroymerlin.data.room.BagDao
import ru.binnyatoff.leroymerlin.data.room.ProductDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val productDao: ProductDao,
    private val bagDao: BagDao,
) {
    private val _productList = MutableStateFlow<List<Product>>(listOf())
    val productList: StateFlow<List<Product>> = _productList

    private val _bagList = MutableStateFlow<List<Product>>(listOf())
    val bagList: StateFlow<List<Product>> = _bagList

    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    private val _bagSize = MutableStateFlow(0)
    val bagSize: StateFlow<Int> = _bagSize

    suspend fun updateInBag(product: Product) {
        productDao.updateInBag(product.productId, !product.inBag)

        val bagDTO = BagDTO(
            productId = product.productId,
            itemWeight = product.itemWeight,
            inBag = !product.inBag,
            inShopList = product.inShopList,
            productName = product.productName,
            twoProductPrice = product.twoProductPrice,
            productPrice = product.productPrice,
            productImage = product.productImage
        )

        if (product.inBag) {
            bagDao.delete(bagDTO.copy(inBag = true))
            getBagFromDatabase()
        } else {
            bagDao.insert(bagDTO)
            getBagFromDatabase()
        }

    }

    fun updateInShopList(productId: Int, inShopList: Boolean) {
        productDao.updateInShopList(productId, inShopList)
    }

    suspend fun getBagFromDatabase() {
        val bagList = bagDao.getAll()
        bagList.size
        _bagList.emit(
            bagList.map {
                Product(
                    productId = it.productId,
                    itemWeight = it.itemWeight,
                    inBag = it.inBag,
                    inShopList = it.inShopList,
                    productName = it.productName,
                    productPrice = it.productPrice,
                    twoProductPrice = it.twoProductPrice,
                    productImage = it.productImage
                )
            })
        getBagSize(bagList)

    }

    suspend fun getFromProductsDatabase() {
        val productList = productDao.getAll()
        if (productList.isEmpty()) {
            saveToDatabase()
        } else {
            _productList.emit(
                productList.map {
                    Product(
                        productId = it.productId,
                        itemWeight = it.itemWeight,
                        inBag = it.inBag,
                        inShopList = it.inShopList,
                        productName = it.productName,
                        productPrice = it.productPrice,
                        twoProductPrice = it.twoProductPrice,
                        productImage = it.productImage
                    )
                })
        }
    }

    suspend fun getBagSize(prductList: List<BagDTO>) {
        val bagsize = prductList.size
        _bagSize.emit(
            bagsize
        )
    }

    suspend fun getProductFromDatabase(productId: Int) {
        val product: Product = productDao.get(productId)
        _product.emit(product)
    }

    private suspend fun saveToDatabase() {
        val productList = ProductListCache()
        productDao.insertAll(productList.list)
        getFromProductsDatabase()
    }
}