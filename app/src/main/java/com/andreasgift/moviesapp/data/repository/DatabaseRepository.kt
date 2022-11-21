package com.andreasgift.moviesapp.data.repository

import com.andreasgift.moviesapp.data.db.MoviesDAO
import com.andreasgift.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repositories to manage data from database
 */
class DatabaseRepository@Inject constructor(
    private val dao: MoviesDAO,
)  {
    fun getMoviesList(): Flow<List<Movie>> = dao.getAllMovies()

    fun getMovieDetailById(id: Int): Flow<Movie> = dao.getMoviesById(id)

    suspend fun syncMovieFromServer(movies: List<Movie>) = dao.deleteAndCreate(movies)
}
