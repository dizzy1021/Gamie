package dev.dizzy1021.core.domain.usecase

import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun callGames(search: String = ""): Flow<ResponseWrapper<List<Game>>>

    fun callGame(id: Int): Flow<ResponseWrapper<Game>>

    fun getFavoriteGames(): Flow<List<Game>>

    fun addFavoriteGame(game: Game)

    fun removeFavoriteGame(id: Int)
}