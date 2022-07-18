package ru.binnyatoff.leroymerlin.ui.screens.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.binnyatoff.leroymerlin.R
import ru.binnyatoff.leroymerlin.data.Category
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.repository.Repository
import javax.inject.Inject

sealed class ProductListState {
    object Loading : ProductListState()
    class Loaded(val productList: List<Product>, val categoryList: List<Category>) :
        ProductListState()

    object Empty : ProductListState()
    class Error(val error: String) : ProductListState()
}

sealed class ProductListEvent {
    object ScreenInit : ProductListEvent()
}

sealed class ProductListAction {
    class ClickedBag(val product: Product) : ProductListAction()
    class ClickedShopList(val productId: Int, val inShopList: Boolean) : ProductListAction()
    object None : ProductListAction()
}

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _viewState = MutableLiveData<ProductListState>()
    val viewState: LiveData<ProductListState> = _viewState

    val categoryList: List<Category> = listOf(Category(
        "Шпаклевки базовые", R.drawable.img_cat_1),
        Category("Шпаклевки финишные", R.drawable.img_cat_2),
        Category("Шпаклевки суперфинишные", R.drawable.img_cat_3))

    fun obtainEvent(event: ProductListEvent) {
        when (event) {
            is ProductListEvent.ScreenInit -> getFromDatabase()
        }
    }

    fun obtainAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.ClickedBag -> updateInBag(action.product)
            is ProductListAction.ClickedShopList -> updateInShopList(action.productId,
                action.inShopList)
        }
    }

    private fun updateInBag(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInBag(product)
            getFromDatabase()
        }
    }


    private fun updateInShopList(productId: Int, inShopList: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInShopList(productId, inShopList)
            getFromDatabase()
        }
    }

    private fun getFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getBagFromDatabase()
            repository.getFromProductsDatabase()
            repository.productList.collect {
                _viewState.postValue(ProductListState.Loaded(it, categoryList))
            }
        }
    }

}