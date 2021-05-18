package dev.dizzy1021.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.dizzy1021.core.data.source.local.dao.GameDao
import dev.dizzy1021.core.data.source.local.entity.GameEntity

@Database(
    version = 2,
    entities = [GameEntity::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}