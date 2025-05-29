package com.example.scrollablelist.model

fun NetworkFilm.toFilm(): Film {
    return Film(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        homepage = null // Default null, atau bisa isi dari API kalau tersedia
    )
}
