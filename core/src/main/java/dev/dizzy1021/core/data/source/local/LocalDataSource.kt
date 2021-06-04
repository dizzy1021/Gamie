package dev.dizzy1021.core.data.source.local

import dev.dizzy1021.core.data.source.local.dao.GameDao
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    fun insertGame(game: GameEntity) = gameDao.insertGame(game)

    fun deleteGame(gameId: Int) = gameDao.deleteGame(gameId)

}