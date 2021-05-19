package dev.dizzy1021.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.dizzy1021.core.domain.usecase.GameUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val gameUseCase: GameUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(gameUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}