package dev.dizzy1021.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.dizzy1021.core.utils.GAME_TABLE

@Entity(tableName = GAME_TABLE)
data class GameEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val name: String,
    val desc: String?,
    val rating: Double,
    val poster: String,
    val website: String?,
    val date: String,
    val publisher: String?,
    val publisherPoster: String?,
    val genres: String?,
    val screenshot: String?,
    val isFavorite: Boolean = false,
)