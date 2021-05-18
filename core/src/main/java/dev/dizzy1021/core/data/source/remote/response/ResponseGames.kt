package dev.dizzy1021.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseGames(

	@field:SerializedName("next")
	val next: String?,

	@field:SerializedName("previous")
	val previous: String?,

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItemGames>
) : Parcelable

@Parcelize
data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platformGames: PlatformGames
) : Parcelable

@Parcelize
data class ResultsItemGames(

	@field:SerializedName("added")
	val added: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("metacritic")
	val metacritic: Int,

	@field:SerializedName("playtime")
	val playtime: Int,

	@field:SerializedName("short_screenshots")
	val shortScreenshots: List<ShortScreenshotsItem>,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItemGames>,

	@field:SerializedName("user_game")
	val userGame: String?,

	@field:SerializedName("rating_top")
	val ratingTop: Int,

	@field:SerializedName("reviews_text_count")
	val reviewsTextCount: Int,

	@field:SerializedName("ratings")
	val ratings: List<RatingsItem>,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("saturated_color")
	val saturatedColor: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("added_by_status")
	val addedByStatus: AddedByStatus,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<ParentPlatformsItem>,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int,

	@field:SerializedName("slug")
	val slug: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("suggestions_count")
	val suggestionsCount: Int,

	@field:SerializedName("stores")
	val stores: List<StoresItemGames>,

	@field:SerializedName("tags")
	val tags: List<TagsItem>,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("tba")
	val tba: Boolean,

	@field:SerializedName("dominant_color")
	val dominantColor: String,

	@field:SerializedName("esrb_rating")
	val esrbRating: EsrbRating,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("updated")
	val updated: String,

	@field:SerializedName("clip")
	val clip: String?,

	@field:SerializedName("reviews_count")
	val reviewsCount: Int
) : Parcelable

@Parcelize
data class RatingsItem(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("percent")
	val percent: Double
) : Parcelable

@Parcelize
data class TagsItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class RequirementsEn(

	@field:SerializedName("minimum")
	val minimum: String,

	@field:SerializedName("recommended")
	val recommended: String
) : Parcelable

@Parcelize
data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("id")
	val id: Int
) : Parcelable

@Parcelize
data class AddedByStatus(

	@field:SerializedName("owned")
	val owned: Int,

	@field:SerializedName("beaten")
	val beaten: Int,

	@field:SerializedName("dropped")
	val dropped: Int,

	@field:SerializedName("yet")
	val yet: Int,

	@field:SerializedName("playing")
	val playing: Int,

	@field:SerializedName("toplay")
	val toplay: Int
) : Parcelable

@Parcelize
data class GenresItem(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class EsrbRating(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class PlatformGames(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class Store(

	@field:SerializedName("games_count")
	val gamesCount: Int,

	@field:SerializedName("domain")
	val domain: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("image_background")
	val imageBackground: String,

	@field:SerializedName("slug")
	val slug: String
) : Parcelable

@Parcelize
data class StoresItemGames(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("store")
	val store: Store
) : Parcelable

@Parcelize
data class PlatformsItemGames(

	@field:SerializedName("requirements_en")
	val requirementsEn: RequirementsEn?,

	@field:SerializedName("released_at")
	val releasedAt: String,

	@field:SerializedName("platform")
	val platformGames: PlatformGames
) : Parcelable
