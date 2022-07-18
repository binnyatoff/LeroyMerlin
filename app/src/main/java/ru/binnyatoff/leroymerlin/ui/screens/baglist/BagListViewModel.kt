package ru.binnyatoff.leroymerlin.ui.screens.baglist

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
class BagListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _viewState = MutableLiveData<BagListState>()
    val viewState: LiveData<BagListState> = _viewState

    fun obtainEvent(event: BagListEvent) {
        when (event) {
            is BagListEvent.ScreenInit -> getFromDatabase()
        }
    }

    fun obtainAction(action: BagListAction) {
        when (action) {
            is BagListAction.ClickedBag -> updateInBag(action.product)
        }
    }


    private fun updateInBag(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateInBag(product)
            getFromDatabase()
        }
    }

    private fun getFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.bagList.collect {
                if (it.isEmpty()) {
                    _viewState.postValue(BagListState.Empty)
                } else {
                    val price = price(it)
                    val weight = weight(it)
                    _viewState.postValue(BagListState.Loaded(it,
                        price = price,
                        weight = weight,
                        amount = it.size))
                }
            }
        }
    }

    private fun price(productList: List<Product>): Int {
        var price = 0
        productList.forEach {
            price += it.productPrice.toInt()
        }
        return price
    }

    private fun weight(productList: List<Product>): Double {
        var weight = 0.0
        productList.forEach {
            weight += it.itemWeight
        }
        return weight
    }
}