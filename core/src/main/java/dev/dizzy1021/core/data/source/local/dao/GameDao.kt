package dev.dizzy1021.core.data.source.local.dao

import androidx.room.*
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import dev.dizzy1021.core.utils.GAME_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM $GAME_TABLE")
    fun getGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM $GAME_TABLE where name LIKE :search")
    fun searchGamesByName(search: String): Flow<List<GameEntity>>

    @Query("SELECT * FROM $GAME_TABLE where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM $GAME_TABLE where id = :id")
    fun getGameById(id: Int): Flow<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBatchGame(game: List<GameEntity>)

    @Update
    fun updateGame(game: GameEntity)
}