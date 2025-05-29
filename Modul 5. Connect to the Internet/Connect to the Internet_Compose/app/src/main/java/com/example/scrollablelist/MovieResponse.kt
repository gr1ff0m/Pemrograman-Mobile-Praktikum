package com.example.scrollablelist.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MovieResponse(
    val page: Int,
    val results: List<NetworkFilm>,
    val total_pages: Int,
    val total_results: Int
)
