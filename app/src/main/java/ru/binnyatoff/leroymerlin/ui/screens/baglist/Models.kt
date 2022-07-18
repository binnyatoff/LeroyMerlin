package ru.binnyatoff.leroymerlin.ui.screens.baglist

import ru.binnyatoff.leroymerlin.data.Product

sealed class BagListState {
    class Loaded(val list: List<Product>, val amount: Int, val weight: Double, val price: Int) :
        BagListState()
    object Empty : BagListState()
}

sealed class BagListEvent {
    object ScreenInit : BagListEvent()
}

sealed class BagListAction {
    class ClickedBag(val product: Product) : BagListAction()
}