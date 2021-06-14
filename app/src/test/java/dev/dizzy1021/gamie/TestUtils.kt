package dev.dizzy1021.gamie

import androidx.arch.core.executor.TaskExecutor
import dev.dizzy1021.core.data.source.local.entity.GameEntity
import dev.dizzy1021.core.data.source.remote.response.*
import dev.dizzy1021.core.domain.model.Game
import dev.dizzy1021.core.utils.ResponseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object InstantTaskExecutor: TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean {
        return true
    }
}

fun generateDataGames(): Flow<ResponseWrapper<List<ResultsItemGames>>> {
    val results  = ArrayList<ResultsItemGames>()
    results.add(
        ResultsItemGames(
            added = 1002,
            rating = 8.2,
            metacritic = 222,
            playtime = 212,
            shortScreenshots = listOf(
                ShortScreenshotsItem(
                    image = "IMAGE URL",
                    id = 102
                )
            ),
            platforms = listOf(
                PlatformsItemGames(
                    requirementsEn = null,
                    releasedAt = "RELEASED AT",
                    platformGames = PlatformGames(
                        name = "NAME_PLATFORM",
                        id = 221,
                        slug = "/PLATFORM-SLUG"
                    )
                )
            ),
            userGame = null,
            ratingTop = 551,
            reviewsTextCount = 1010,
            ratings = listOf(
                RatingsItem(
                    count = 221,
                    id = 2111,
                    title = "RATING TITLE",
                    percent = 8.2
                )
            ),
            genres = listOf(
                GenresItem(
                    gamesCount = 22,
                    name = "Adventure",
                    id = 222,
                    imageBackground = "URL GENRE",
                    slug = "/GENRE-SLUG"
                )
            ),
            saturatedColor = "SATURATED",
            id = 211,
            addedByStatus = AddedByStatus(
                owned = 30,
                beaten = 32,
                dropped = 22,
                yet = 112,
                playing = 21,
                toplay = 11
            ),
            parentPlatforms = listOf(
                ParentPlatformsItem(
                    platformGames = PlatformGames(
                        name = "PLATFORM NAME",
                        id = 21,
                        slug = "/PLATFORM-SLUG"
                    )
                )
            ),
            ratingsCount = 112,
            slug = "/SLUG",
            released = "2021",
            suggestionsCount = 1112,
            stores = listOf(
                StoresItemGames(
                    id = 211,
                    store = Store(
                        gamesCount = 93,
                        domain = "DOMAIN",
                        name = "NAME",
                        id = 11,
                        imageBackground = "URL IMAGE",
                        slug = "/STORE-SLUG"
                    )
                )
            ),
            tags = listOf(
                TagsItem(
                    gamesCount = 20,
                    name = "TAGS NAME",
                    language = "EN",
                    id = 22,
                    imageBackground = "URL IMAGE",
                    slug = "/SLUG"
                )
            ),
            backgroundImage = "URL IMAGE",
            tba = false,
            dominantColor = "COLOR",
            esrbRating = EsrbRating(
                name = "NAME",
                id = 73,
                slug = "/ESRB-SLUG"
            ),
            name = "GAME NAME",
            updated = "2022",
            clip = null,
            reviewsCount = 222
        )
    )

    return flow {
        emit(ResponseWrapper.success(results))
    }
}

fun generateDataGame(): Flow<ResponseWrapper<ResponseGame>> {
    val results  = ResponseGame(
        added = 1002,
        rating = 8.2,
        developers = listOf(
            DevelopersItem(
                gamesCount = 11,
                name = "DEVELOPER",
                id = 12,
                imageBackground = "URL IMAGE",
                slug = "/DEVELOPER-SLUG"
            )
        ),
        name = "GAME NAME",
        gameSeriesCount = 32,
        metacritic = 222,
        playtime = 212,
        platforms = listOf(
            PlatformsItem(
                releasedAt = "RELEASED AT",
                requirements = Requirements(
                   minimum = "MINIMUM",
                   recommended = "RECOMMENDED"
               ),
                platform = Platform(
                    name = "PLATFORM",
                    platform = 22,
                    slug = "/PLATFORM-SLUG"
                )
            )
        ),
        userGame = "USER GAME",
        ratingTop = 551,
        reviewsTextCount = 1010,
        publishers = listOf(null),
        achievementsCount = 23,
        redditCount = 221,
        redditName = "REDDIT NAME",

        ratings = listOf(
            RatingsItem(
                count = 221,
                id = 2111,
                title = "RATING TITLE",
                percent = 8.2
            )
        ),
        genres = listOf(
            GenresItem(
                gamesCount = 22,
                name = "Adventure",
                id = 222,
                imageBackground = "URL GENRE",
                slug = "/GENRE-SLUG"
            )
        ),
        saturatedColor = "SATURATED",
        id = 211,
        addedByStatus = AddedByStatus(
            owned = 30,
            beaten = 32,
            dropped = 22,
            yet = 112,
            playing = 21,
            toplay = 11
        ),
        parentPlatforms = listOf(
            ParentPlatformsItem(
                platformGames = PlatformGames(
                    name = "PLATFORM NAME",
                    id = 21,
                    slug = "/PLATFORM-SLUG"
                )
            )
        ),
        ratingsCount = 112,
        slug = "/SLUG",
        released = "2021",
        youtubeCount = 21,
        moviesCount = 33,
        description = "DESCRIPTION",
        descriptionRaw = "DESCRIPTION RAW",
        redditDescription = "DESCRIPTION REDDIT",
        redditLogo = "REDDIT LOGO",
        suggestionsCount = 1112,
        stores = listOf(
            StoresItem(
                id = 211,
                store = Store(
                    gamesCount = 93,
                    domain = "DOMAIN",
                    name = "NAME",
                    id = 11,
                    imageBackground = "URL IMAGE",
                    slug = "/STORE-SLUG"
                ),
                url = "STORE URL"
            )
        ),
        tags = listOf(
            TagsItem(
                gamesCount = 20,
                name = "TAGS NAME",
                language = "EN",
                id = 22,
                imageBackground = "URL IMAGE",
                slug = "/SLUG"
            )
        ),
        backgroundImage = "URL IMAGE",
        tba = false,
        dominantColor = "COLOR",
        esrbRating = EsrbRating(
            name = "NAME",
            id = 73,
            slug = "/ESRB-SLUG"
        ),
        updated = "2022",
        clip = "CLIP",
        reviewsCount = 222,
        additionsCount = 211,
        nameOriginal = "ORIGINAL NAME GAME",
        metacriticUrl = "URL METACRITIC",
        alternativeNames = listOf("NAME 1", "NAME 2"),
        parentsCount = 21,
        parentAchievementsCount = 123,
        metacriticPlatforms = listOf(
            MetacriticPlatformsItem(
                metascore = 11,
                url = "METACRITIC URL",
                platform = Platform(
                    name = "PLATFORM",
                    platform = 22,
                    slug = "/PLATFORM-SLUG"
                )
            )
        ),
        creatorsCount = 77,
        redditUrl = "REDDIT URL",
        website = "WEBSITE",
        twitchCount = 22,
        backgroundImageAdditional = "ADDITIONAL BACKGROUND",
        screenshotsCount = 5
    )

    return flow {
        emit(ResponseWrapper.success(results))
    }
}

fun generateEntityGames(): Flow<List<GameEntity>> {
    val results = ArrayList<GameEntity>()
    results.add(
        GameEntity(
            id = 3,
            name = "GAME NAME",
            desc = "DESCRIPTION",
            rating = 8.3,
            poster = "URL IMAGE",
            website = "WEBSITE",
            date = "DATE",
            publisher = "PUBLISHER",
            publisherPoster = "URL POSTER PUBLISHER",
            genres = "GENRES",
            screenshot = "IMAGE URL",
            isFavorite = true
        )
    )

    return flow {
        emit(results)
    }
}

fun generateEntityGame(): Flow<GameEntity> {
    val results = GameEntity(
        id = 3,
        name = "GAME NAME",
        desc = "DESCRIPTION",
        rating = 8.3,
        poster = "URL IMAGE",
        website = "WEBSITE",
        date = "DATE",
        publisher = "PUBLISHER",
        publisherPoster = "URL POSTER PUBLISHER",
        genres = "GENRES",
        screenshot = "IMAGE URL",
        isFavorite = true
    )

    return flow {
        emit(results)
    }
}

fun generateGames(): List<Game> {
    val results = ArrayList<Game>()

    results.add(
        Game(
            id = 1,
            name = "NAME",
            desc = "DESCRIPTION",
            rating = 0.0,
            poster = null,
            website = null,
            date = null,
            publisher = null,
            publisherPoster = null,
            genres = null,
            screenshot = null,
            isFavorite = true
        )
    )

    return results
}

fun generateGame(): Game {

    return Game(
        id = 1,
        name = "NAME",
        desc = "DESCRIPTION",
        rating = 0.0,
        poster = null,
        website = null,
        date = null,
        publisher = null,
        publisherPoster = null,
        genres = null,
        screenshot = null,
        isFavorite = true
    )
}
