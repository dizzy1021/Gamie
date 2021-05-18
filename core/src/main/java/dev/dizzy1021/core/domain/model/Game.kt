package dev.dizzy1021.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val desc: String?,
    val rating: Double,
    val poster: String,
    val website: String?,
    val date: String,
    val publisher: String?,
    val publisherPoster: String?,
    val genres: String?,
    val screenshot: String?,
    val isFavorite: Boolean = false,
): Parcelable