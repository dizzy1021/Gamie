package dev.dizzy1021.search

import dagger.Component
import dev.dizzy1021.gamie.di.DynamicFeaturesDependencies

@Component(dependencies = [DynamicFeaturesDependencies::class])
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeaturesDependencies): SearchComponent
    }

}