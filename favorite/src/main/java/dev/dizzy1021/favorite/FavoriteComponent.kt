package dev.dizzy1021.favorite

import dagger.Component
import dev.dizzy1021.gamie.di.DynamicFeaturesDependencies

@Component(dependencies = [DynamicFeaturesDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeaturesDependencies): FavoriteComponent
    }

}