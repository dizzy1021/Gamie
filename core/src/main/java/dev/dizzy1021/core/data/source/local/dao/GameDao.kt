package dev.dizzy1021.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import dev.dizzy1021.core.utils.GAME_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM $GAME_TABLE where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGame(game: GameEntity)

    @Query("DELETE FROM $GAME_TABLE WHERE id = :gameId")
    fun deleteGame(gameId: Int)
}