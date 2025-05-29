package com.example.scrollablelist

import android.app.Application
import com.example.scrollablelist.data.FilmRepository
import com.example.scrollablelist.network.TmdbApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import timber.log.Timber

class MyApplication : Application() {

    lateinit var filmRepository: FilmRepository
        private set

    lateinit var preferenceManager: PreferenceManager
        private set

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        preferenceManager = PreferenceManager(this)

        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }

        val okHttpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        val api = retrofit.create(TmdbApi::class.java)

        val apiKey = "9fad0dc9a0338ecf00596875e4cf5645"

        filmRepository = FilmRepository(api, apiKey)
    }
}
