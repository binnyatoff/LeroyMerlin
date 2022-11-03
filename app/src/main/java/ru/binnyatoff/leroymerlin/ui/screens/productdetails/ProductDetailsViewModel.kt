package ru.binnyatoff.leroymerlin.ui.screens.productdetails

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
class ProductDetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
   private val _viewState = MutableLiveData<ProductDetailsState>()
   val viewState: LiveData<ProductDetailsState> = _viewState

    fun obtainEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.ScreenInit -> getFromDatabase(event.productId)
        }
    }

    fun obtainAction(action: ProductDetailsAction) {
        when (action) {
            is ProductDetailsAction.ClickedBag -> updateInBag(action.product)
            is ProductDetailsAction.ClickedShopList -> updateInShopList(action.productId,action.inShopList)
        }
    }

    private fun updateInBag(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInBag(product)
            getFromDatabase(product.productId)
        }
    }


    private fun updateInShopList(productId: Int, inShopList:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInShopList(productId, !inShopList)
            getFromDatabase(productId)
        }
    }

    private fun getFromDatabase(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductFromDatabase(productId)
            repository.product.collect {product ->
                if (product!=null) {
                    _viewState.postValue(ProductDetailsState.Loaded(product))
                }else
                    _viewState.postValue(ProductDetailsState.Empty)
            }
        }
    }

}