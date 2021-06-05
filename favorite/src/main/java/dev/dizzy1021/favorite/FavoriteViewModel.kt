package dev.dizzy1021.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    useCase: GameUseCase
): ViewModel() {

    val games = useCase.getFavoriteGames().asLiveData()

}