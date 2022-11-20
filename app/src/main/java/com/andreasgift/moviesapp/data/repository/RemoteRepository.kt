package com.andreasgift.moviesapp.data.repository

import com.andreasgift.moviesapp.data.api.MovieApi
import com.andreasgift.moviesapp.data.model.Movie
import com.andreasgift.moviesapp.data.model.Results
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Repositories to fetch data from network
 */
class RemoteRepository@Inject constructor(
    private val retrofitService: MovieApi,
)  {
    suspend fun getMoviesList(): Response<Results> = retrofitService.fetchMovies()

    suspend fun getMovieDetail(movieId: String): Response<Movie> = retrofitService.fetchMovieDetail(movieId)
}
