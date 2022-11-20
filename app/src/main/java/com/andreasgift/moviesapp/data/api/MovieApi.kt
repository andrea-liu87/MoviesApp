package com.andreasgift.moviesapp.data.api

import android.util.Log
import com.andreasgift.moviesapp.data.model.Movie
import com.andreasgift.moviesapp.data.model.Results
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * API communication setup
 */
interface MovieApi {

    @GET("discover/movie")
    suspend fun fetchMovies(): Response<Results>

    @GET("movies/{MOVIE_ID}")
    suspend fun fetchMovieDetail(@Path("MOVIE_ID") movieId: String): Response<Movie>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val APIKEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"

        fun create(): MovieApi {
            val logger = HttpLoggingInterceptor { Log.d("MOVIES-API", it) }
            logger.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val url = chain.request().url.newBuilder().addQueryParameter("api_key", APIKEY).build()
                    val newRequest = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(logger)
                .build()

            val gson = GsonBuilder()
                .create()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(MovieApi::class.java)
        }
    }
}