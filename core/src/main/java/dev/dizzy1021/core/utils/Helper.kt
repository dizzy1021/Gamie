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

fun List<ResultsItemGames>.toModel(): List<Game> =
    this.map {
        Game(
            id = it.id,
            name = it.name,
            desc = null,
            rating = it.rating,
            poster = it.backgroundImage,
            website = null,
            date = it.released,
            publisher = null,
            publisherPoster = null,
            genres = it.genres.joinToString { a -> a.name },
            screenshot = it.shortScreenshots.joinToString { a -> a.image },
            isFavorite = false
        )
    }

fun ResponseGame.toModel(save: Game? = null): Game =
    Game(
            id = this.id,
            name = this.name,
            desc = this.descriptionRaw,
            rating = this.rating,
            poster = this.backgroundImage,
            website = this.website,
            date = this.released,
            publisher = if (this.publishers.isNotEmpty()) this.publishers[0]?.name else "",
            publisherPoster = if (this.publishers.isNotEmpty()) this.publishers[0]?.imageBackground else "",
            genres = save?.genres,
            screenshot = save?.screenshot,
            isFavorite = save?.isFavorite == true
        )

fun GameEntity.toModel(): Game =
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
        screenshot = this.screenshot,
        isFavorite = this.isFavorite
    )