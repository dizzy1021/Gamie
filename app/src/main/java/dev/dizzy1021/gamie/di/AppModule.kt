@file:Suppress("unused")

package dev.dizzy1021.gamie.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.domain.usecase.GameUseCase
import dev.dizzy1021.core.domain.usecase.ImplGameRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideGameUseCase(implGameRepository: ImplGameRepository): GameUseCase

}