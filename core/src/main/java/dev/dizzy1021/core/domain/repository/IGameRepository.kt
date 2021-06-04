package dev.dizzy1021.core.domain.repository

import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun callGames(search: String = ""): Flow<ResponseWrapper<List<Game>>>

    fun callGame(id: Int): Flow<ResponseWrapper<Game>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun addFavoriteGame(game: Game)

    fun removeFavoriteGame(id: Int)

}