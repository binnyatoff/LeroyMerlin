package ru.binnyatoff.leroymerlin.ui.screens.productlist

import ru.binnyatoff.leroymerlin.data.Category
import ru.binnyatoff.leroymerlin.data.Product


sealed class ProductListState {
    class Loaded(val productList: List<Product>, val categoryList: List<Category>) :
        ProductListState()
}

sealed class ProductListEvent {
    object ScreenInit : ProductListEvent()
}

sealed class ProductListAction {
    class ClickedBag(val product: Product) : ProductListAction()
    class ClickedShopList(val productId: Int, val inShopList: Boolean) : ProductListAction()
}