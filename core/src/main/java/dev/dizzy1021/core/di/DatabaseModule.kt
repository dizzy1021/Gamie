package dev.dizzy1021.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.data.source.local.AppDatabase
import dev.dizzy1021.core.data.source.local.dao.GameDao
import dev.dizzy1021.core.utils.DB_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideGameDao(db: AppDatabase): GameDao = db.gameDao()
}