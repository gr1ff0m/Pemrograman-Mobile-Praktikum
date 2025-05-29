package com.example.scrollablelist.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitInstance {

    private val json = Json {
        ignoreUnknownKeys = true // biar bisa ignore field yg gak dipakai
    }

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") // base URL TMDb API
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val api: TmdbApi by lazy {
        retrofit.create(TmdbApi::class.java)
    }
}
