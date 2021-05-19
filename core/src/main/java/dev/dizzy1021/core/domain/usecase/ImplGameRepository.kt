package dev.dizzy1021.core.domain.usecase

import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.repository.IGameRepository
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplGameRepository @Inject constructor(
    private val gameRepository: IGameRepository
): GameUseCase {

    override fun callGames(search: String): Flow<ResponseWrapper<List<Game>>> = gameRepository.callGames(search)

    override fun callGame(id: Int): Flow<ResponseWrapper<Game>> = gameRepository.callGame(id)

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGame()

    override fun updateGame(game: Game) = gameRepository.updateGame(game)
}