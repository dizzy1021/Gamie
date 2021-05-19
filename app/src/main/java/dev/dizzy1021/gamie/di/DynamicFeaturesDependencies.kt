package dev.dizzy1021.gamie.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.dizzy1021.core.domain.usecase.GameUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DynamicFeaturesDependencies {

    fun gameUseCase(): GameUseCase
}