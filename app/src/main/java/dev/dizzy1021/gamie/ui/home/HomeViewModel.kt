package dev.dizzy1021.gamie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.usecase.GameUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GameUseCase
) : ViewModel() {

    val games = useCase.callGames().asLiveData()

}