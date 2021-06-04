package dev.dizzy1021.core.data.source

import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.repository.IGameRepository
import dev.dizzy1021.core.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {

    override fun callGames(search: String): Flow<ResponseWrapper<List<Game>>> =
        flow {
            EspressoIdlingResource.increment()
            emit(ResponseWrapper.pending(null))

            val response = remoteDataSource.getGames(search = search)
                .first()

            when (response.state) {
                State.SUCCESS -> {
                    val result = response.data?.toModel()

                    emit(ResponseWrapper.success(result))
                }
                State.FAILURE -> {
                    emit(ResponseWrapper.failure(response.message.toString(), null))
                }
                State.PENDING -> {}
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)

    override fun callGame(id: Int): Flow<ResponseWrapper<Game>> =
        flow {
            EspressoIdlingResource.increment()
            emit(ResponseWrapper.pending(null))

            val response = remoteDataSource.getGame(id = id)
                .first()

            when (response.state) {
                State.SUCCESS -> {
                    val result = response.data?.toModel()

                    emit(ResponseWrapper.success(result))
                }
                State.FAILURE -> {
                    emit(ResponseWrapper.failure(response.message.toString(), null))
                }
                State.PENDING -> {}
            }
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)


    override fun getFavoriteGame(): Flow<List<Game>> {
        EspressoIdlingResource.increment()
        val result = localDataSource.getFavoriteGames().map {
            it.toDomain()
        }
        EspressoIdlingResource.decrement()

        return result
    }


    override fun addFavoriteGame(game: Game) {
        EspressoIdlingResource.increment()
        appExecutors.diskIO().execute {
            localDataSource.insertGame(game.toEntity())
        }
        EspressoIdlingResource.decrement()
    }

    override fun removeFavoriteGame(id: Int) {
        EspressoIdlingResource.increment()
        appExecutors.diskIO().execute {
            localDataSource.deleteGame(id)
        }
        EspressoIdlingResource.decrement()
    }

}



