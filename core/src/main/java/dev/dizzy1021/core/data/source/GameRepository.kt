package dev.dizzy1021.core.data.source

import dev.dizzy1021.core.data.source.local.LocalDataSource
import dev.dizzy1021.core.data.source.remote.RemoteDataSource
import dev.dizzy1021.core.data.source.remote.response.ResponseGame
import dev.dizzy1021.core.data.source.remote.response.ResultsItemGames
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.domain.repository.IGameRepository
import dev.dizzy1021.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IGameRepository {

    override fun callGames(): Flow<ResponseWrapper<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<ResultsItemGames>>() {
            override fun loadFromDB(): Flow<List<Game>> =
                localDataSource.getGames().map {
                    it.toDomain()
                }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ResponseWrapper<List<ResultsItemGames>>> =
                remoteDataSource.getGames()

            override suspend fun saveCallResult(data: List<ResultsItemGames>, save: List<Game>) {
                val list = data.toEntities()
                localDataSource.insertBatchGame(list)
            }

        }.asFlow()

    override fun callGame(id: Int): Flow<ResponseWrapper<Game>> =
        object : NetworkBoundResource<Game, ResponseGame>() {
            override fun loadFromDB(): Flow<Game> =
                localDataSource.getGameById(id).map {
                    it.toDomain()
                }

            override fun shouldFetch(data: Game?): Boolean = data == null || data.desc == ""

            override suspend fun createCall(): Flow<ResponseWrapper<ResponseGame>> =
                remoteDataSource.getGame(id)

            override suspend fun saveCallResult(data: ResponseGame, save: Game) {
                val game = data.toEntity(save)
                appExecutors.diskIO().execute { localDataSource.updateGame(game) }
            }

        }.asFlow()


    override fun getFavoriteGame(): Flow<List<Game>> {
        EspressoIdlingResource.increment()
        val result = localDataSource.getFavoriteGames().map {
            it.toDomain()
        }
        EspressoIdlingResource.decrement()

        return result
    }


    override fun updateGame(game: Game) {
        EspressoIdlingResource.increment()
        appExecutors.diskIO().execute { localDataSource.updateGame(game.toEntity()) }
        EspressoIdlingResource.decrement()
    }

}



