package dev.dizzy1021.core.data.source.remote.api

import dev.dizzy1021.core.BuildConfig
import dev.dizzy1021.core.data.source.remote.response.ResponseGame
import dev.dizzy1021.core.data.source.remote.response.ResponseGames
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET("games")
    suspend fun callGames(
        @Query("key") key: String = BuildConfig.RAWG_TOKEN
    ): Response<ResponseGames>

    @GET("games/{id}")
    suspend fun callGame(
        @Path("id") id: Int,
        @Query("key") key: String = BuildConfig.RAWG_TOKEN
    ): Response<ResponseGame>

}