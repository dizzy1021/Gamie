package dev.dizzy1021.core.utils

import dev.dizzy1021.core.data.source.local.entity.GameEntity
import dev.dizzy1021.core.data.source.remote.response.ResponseGame
import dev.dizzy1021.core.data.source.remote.response.ResultsItemGames
import dev.dizzy1021.core.domain.model.Game

fun List<GameEntity>.toDomain(): List<Game> =
    this.map {
        Game(
            id = it.id,
            name = it.name,
            desc = it.desc,
            rating = it.rating,
            poster = it.poster,
            website = it.website,
            date = it.date,
            publisher = it.publisher,
            publisherPoster = it.publisherPoster,
            genres = it.genres,
            screenshot = it.genres,
            isFavorite = it.isFavorite
        )
    }

fun GameEntity.toDomain(): Game =
        Game(
            id = this.id,
            name = this.name,
            desc = this.desc,
            rating = this.rating,
            poster = this.poster,
            website = this.website,
            date = this.date,
            publisher = this.publisher,
            publisherPoster = this.publisherPoster,
            genres = this.genres,
            screenshot = this.genres,
            isFavorite = this.isFavorite
        )

fun Game.toEntity(): GameEntity =
    GameEntity(
        id = this.id,
        name = this.name,
        desc = this.desc,
        rating = this.rating,
        poster = this.poster,
        website = this.website,
        date = this.date,
        publisher = this.publisher,
        publisherPoster = this.publisherPoster,
        genres = this.genres,
        screenshot = this.genres,
        isFavorite = this.isFavorite
    )

fun List<ResultsItemGames>.toEntities(): List<GameEntity> =
    this.map {
        GameEntity(
            id = it.id,
            name = it.name,
            desc = "",
            rating = it.rating,
            poster = it.backgroundImage,
            website = "",
            date = it.released,
            publisher = "",
            publisherPoster = "",
            genres = it.genres.joinToString { a -> a.name },
            screenshot = it.shortScreenshots.joinToString { a -> a.image },
            isFavorite = false
        )
    }

fun ResponseGame.toEntity(save: Game? = null): GameEntity =
        GameEntity(
            id = this.id,
            name = this.name,
            desc = this.description,
            rating = this.rating,
            poster = this.backgroundImage,
            website = this.website,
            date = this.released,
            publisher = this.publishers[0]?.name,
            publisherPoster = this.publishers[0]?.name,
            genres = save?.genres,
            screenshot = save?.screenshot,
            isFavorite = save?.isFavorite == true
        )