package ru.binnyatoff.leroymerlin.ui.screens.productdetails

import ru.binnyatoff.leroymerlin.data.Product

sealed class ProductDetailsState {
    class Loaded(val product: Product) : ProductDetailsState()
    object Empty : ProductDetailsState()
}
sealed class ProductDetailsEvent(){
    class ScreenInit(val productId: Int): ProductDetailsEvent()
}

sealed class ProductDetailsAction {
    class ClickedBag(val product: Product) : ProductDetailsAction()
    class ClickedShopList(val productId: Int, val inShopList: Boolean) : ProductDetailsAction()
}