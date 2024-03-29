package ru.binnyatoff.leroymerlin.ui.screens.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.binnyatoff.leroymerlin.data.Product
import ru.binnyatoff.leroymerlin.repository.Repository
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _viewState = MutableLiveData<ProductListState>()
    val viewState: LiveData<ProductListState> = _viewState

    fun obtainEvent(event: ProductListEvent) {
        when (event) {
            is ProductListEvent.ScreenInit -> getFromDatabase()
        }
    }

    fun obtainAction(action: ProductListAction) {
        when (action) {
            is ProductListAction.ClickedBag -> updateInBag(action.product)
            is ProductListAction.ClickedShopList -> updateInShopList(
                action.productId,
                action.inShopList
            )
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
            repository.getCategoryList()

            repository.productList.collect { productList ->
                repository.categoryList.collect { categoryList ->
                    _viewState.postValue(ProductListState.Loaded(productList, categoryList))

                }
            }
        }
    }

}