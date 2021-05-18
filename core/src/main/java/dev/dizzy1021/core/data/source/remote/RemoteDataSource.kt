package dev.dizzy1021.core.data.source.remote

import dev.dizzy1021.core.data.source.remote.api.Services
import dev.dizzy1021.core.data.source.remote.response.ResponseGame
import dev.dizzy1021.core.data.source.remote.response.ResultsItemGames
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val services: Services) {

    suspend fun getGames(): Flow<ResponseWrapper<List<ResultsItemGames>>> {
        return flow {
            services.callGames().let {
               if (it.isSuccessful) {
                   emit(ResponseWrapper.success(it.body()?.results))
               } else {
                   emit(ResponseWrapper.failure("Failure when calling data", null))
               }
           }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGame(id: Int): Flow<ResponseWrapper<ResponseGame>> {
        return flow {
            services.callGame(id = id).let {
                if (it.isSuccessful) {
                    emit(ResponseWrapper.success(it.body()))
                } else {
                    emit(ResponseWrapper.failure("Failure when calling data", null))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

}