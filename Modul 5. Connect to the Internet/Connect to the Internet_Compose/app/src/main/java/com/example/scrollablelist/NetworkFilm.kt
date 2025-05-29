package com.example.scrollablelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.InternalSerializationApi
import kotlin.annotation.AnnotationTarget.CLASS

@OptIn(InternalSerializationApi::class)
@Serializable
data class NetworkFilm(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path") val backdropPath: String?,
    @SerialName("release_date") val releaseDate: String?
)
