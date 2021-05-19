package dev.dizzy1021.core.data.source.local

import dev.dizzy1021.core.data.source.local.dao.GameDao
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    fun getGames(search: String = ""): Flow<List<GameEntity>> =
        if (search != "") gameDao.searchGamesByName("%$search%")
        else gameDao.getGames()


    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    fun getGameById(id: Int): Flow<GameEntity> = gameDao.getGameById(id)

    suspend fun insertBatchGame(games: List<GameEntity>) = gameDao.insertBatchGame(games)

    fun updateGame(game: GameEntity) = gameDao.updateGame(game)

}