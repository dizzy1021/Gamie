package dev.dizzy1021.search

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dev.dizzy1021.gamie.di.DynamicFeaturesDependencies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@Component(dependencies = [DynamicFeaturesDependencies::class])
interface SearchComponent {

    @FlowPreview
    fun inject(fragment: SearchFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependencies: DynamicFeaturesDependencies): Builder
        fun build(): SearchComponent
    }

}