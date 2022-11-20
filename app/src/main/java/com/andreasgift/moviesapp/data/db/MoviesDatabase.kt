package com.andreasgift.moviesapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andreasgift.moviesapp.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = true)
abstract class MoviesDB : RoomDatabase() {

    abstract fun dao(): MoviesDAO

    companion object {
        @Volatile
        private var instance: MoviesDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDB::class.java,
                "movies.db"
            ).fallbackToDestructiveMigration()
                .build()
    }
}