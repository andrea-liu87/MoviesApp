package com.andreasgift.moviesapp.data.api

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.andreasgift.moviesapp.data.repository.DatabaseRepository
import com.andreasgift.moviesapp.data.repository.RemoteRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@EntryPoint
@InstallIn(SingletonComponent::class)
@Named("remote_repo")
interface RemoteRepoEntryPoint {
    val remoteRepo: RemoteRepository
}

@EntryPoint
@InstallIn(SingletonComponent::class)
@Named("database_repo")
interface DatabaseRepoEntryPoint {
    val dbRepo: DatabaseRepository
}

@Singleton
class ListMovieDownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    @Inject
    lateinit var remoteRepo: RemoteRepository

    @Inject
    lateinit var dbRepo: DatabaseRepository

    init {
        val remoteRepoFactory = EntryPointAccessors.fromApplication(
            appContext,
            RemoteRepoEntryPoint::class.java
        )
        remoteRepo = remoteRepoFactory.remoteRepo

        val dbRepoFactory = EntryPointAccessors.fromApplication(
            appContext,
            DatabaseRepoEntryPoint::class.java
        )
        dbRepo = dbRepoFactory.dbRepo
    }

    override fun doWork(): Result {
        return try {
            fetchMoviesList(remoteRepo, dbRepo)
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun fetchMoviesList(repo: RemoteRepository, dbRepo: DatabaseRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getMoviesList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    if (list != null) {
                        dbRepo.syncMovieFromServer(list)
                    }
                } else {
                    val errorMessage = "Error " + response.code().toString()
                    Log.d(this@ListMovieDownloadWorker.applicationContext.packageName, errorMessage)
                }
            }
        }
    }
}