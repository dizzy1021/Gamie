package dev.dizzy1021.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.usecase.GameUseCase
import dev.dizzy1021.core.utils.ResponseWrapper
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: GameUseCase
): ViewModel() {

    val games: (String) -> LiveData<ResponseWrapper<List<Game>>> = { search->
        Log.d("SearchFunction", "ViewModel - $search")
        useCase.callGames(search).asLiveData()
    }

}