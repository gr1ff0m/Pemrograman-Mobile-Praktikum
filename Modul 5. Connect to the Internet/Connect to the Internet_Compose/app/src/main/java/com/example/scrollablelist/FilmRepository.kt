package com.example.scrollablelist.data

import com.example.scrollablelist.model.Film
import com.example.scrollablelist.network.TmdbApi

class FilmRepository(
    private val api: TmdbApi,
    private val apiKey: String
) {
    suspend fun getPopularFilms(): List<Film> {
        val response = api.getPopularMovies(apiKey)

        return response.results.map { networkFilm ->
            Film(
                id = networkFilm.id,
                title = networkFilm.title,
                overview = networkFilm.overview,
                posterPath = networkFilm.posterPath,
                backdropPath = networkFilm.backdropPath,
                releaseDate = networkFilm.releaseDate,
                homepage = null
            )
        }
    }
}
