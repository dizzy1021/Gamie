package dev.dizzy1021.core.domain.usecase

import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.repository.IGameRepository
import dev.dizzy1021.core.utils.AppExecutors
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplGameRepository @Inject constructor(
    private val gameRepository: IGameRepository,
    private val executors: AppExecutors,
): GameUseCase {

    override fun callGames(search: String): Flow<ResponseWrapper<List<Game>>> = gameRepository.callGames(search)

    override fun callGame(id: Int): Flow<ResponseWrapper<Game>> = gameRepository.callGame(id)

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGame()

    override fun addFavoriteGame(game: Game) {
        executors.diskIO().execute {
            gameRepository.addFavoriteGame(game)
        }
    }

    override fun removeFavoriteGame(id: Int) {
        executors.diskIO().execute {
            gameRepository.removeFavoriteGame(id)
        }
    }
}