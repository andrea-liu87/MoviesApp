package com.andreasgift.moviesapp.data.di

import android.content.Context
import androidx.room.Room
import com.andreasgift.moviesapp.MovieApplication
import com.andreasgift.moviesapp.data.api.MovieApi
import com.andreasgift.moviesapp.data.db.MoviesDAO
import com.andreasgift.moviesapp.data.db.MoviesDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideApplication(@ApplicationContext context: Context): MovieApplication =
        context as MovieApplication

    @Provides
    fun provideAPIService(@ApplicationContext context: Context): MovieApi {
        return MovieApi.create()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(database: MoviesDB): MoviesDAO =
        database.dao()

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext appContext: Context): MoviesDB {
        return Room.databaseBuilder(
            appContext,
            MoviesDB::class.java,
            "movies.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}
