package com.andreasgift.moviesapp.data.db

import androidx.room.*
import com.andreasgift.moviesapp.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {
    @Transaction
    suspend fun deleteAndCreate(list: List<Movie>) {
        deleteAllMovies()
        insertAll(list)
    }

    @Query("SELECT * FROM movies ORDER BY id ASC")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE id IN (:id)")
    fun getMoviesById(id:Int): Flow<Movie>

    @Delete
    fun deleteMovie(form: Movie)

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateMovie(movie: Movie)

    @Insert
    fun insertAll(movieList: List<Movie>)

}