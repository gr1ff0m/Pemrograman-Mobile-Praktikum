package com.example.scrollablelist.model

data class Film(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val homepage: String? = null
)
