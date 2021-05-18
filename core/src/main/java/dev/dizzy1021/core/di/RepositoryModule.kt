package dev.dizzy1021.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.data.source.GameRepository
import dev.dizzy1021.core.domain.repository.IGameRepository

@Module(includes = [APIModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(gameRepository: GameRepository): IGameRepository
}