package ru.binnyatoff.leroymerlin.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.binnyatoff.leroymerlin.repository.Repository
import javax.inject.Inject

@HiltViewModel
class BottomViewModel @Inject constructor(repository: Repository):ViewModel() {
    val bagSize = repository.bagSize
}