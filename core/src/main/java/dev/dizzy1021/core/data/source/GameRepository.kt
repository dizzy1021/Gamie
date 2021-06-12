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
): IGameRepository {

    override fun callGames(search: String): Flow<ResponseWrapper<List<Game>>> =
        flow {
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
        }.flowOn(Dispatchers.IO)

    override fun callGame(id: Int): Flow<ResponseWrapper<Game>> =
        flow {
            emit(ResponseWrapper.pending(null))

            val local = localDataSource.getGame(id).firstOrNull()

            if (local != null) {
                emit(ResponseWrapper.success(local.toModel()))
            } else {
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
            }

        }.flowOn(Dispatchers.IO)


    override fun getFavoriteGame(): Flow<List<Game>> =
        localDataSource.getFavoriteGames().map {
            it.toDomain()
        }


    override fun addFavoriteGame(game: Game) {
        localDataSource.insertGame(game.toEntity())
    }

    override fun removeFavoriteGame(id: Int) {
        localDataSource.deleteGame(id)
    }

}



