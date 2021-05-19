package dev.dizzy1021.gamie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.usecase.GameUseCase
import dev.dizzy1021.core.utils.ResponseWrapper
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GameUseCase
) : ViewModel() {

    val game: (Int) -> LiveData<ResponseWrapper<Game>> = { id ->
        useCase.callGame(id).asLiveData()
    }

    fun addFavorite(game: Game) {
        useCase.updateGame(game)
    }

}