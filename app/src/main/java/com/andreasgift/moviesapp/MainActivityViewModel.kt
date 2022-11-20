package com.andreasgift.moviesapp

import android.util.Log
import androidx.lifecycle.*
import androidx.work.*
import com.andreasgift.moviesapp.data.api.ListMovieDownloadWorker
import com.andreasgift.moviesapp.data.model.Movie
import com.andreasgift.moviesapp.data.repository.DatabaseRepository
import com.andreasgift.moviesapp.data.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    repo: DatabaseRepository
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = repo.getMoviesList().map {
        MainActivityUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    private var workManager = WorkManager.getInstance(MovieApplication())
    private val networkConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val formTemplateWorkInfoItems: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosByTagLiveData("download_movies")

    internal fun updateDatabase(lifecycleOwner: LifecycleOwner) {
        val syncTemplateDBRequest = OneTimeWorkRequestBuilder<ListMovieDownloadWorker>()
            .addTag("download_movies")
            .setConstraints(networkConstraints)
            .build()

        workManager.enqueue(syncTemplateDBRequest)

        formTemplateWorkInfoItems.observe(lifecycleOwner, Observer { list ->
            val workInfo = list[0]
            if (list.isNotEmpty() && workInfo.state.isFinished) {
                Log.d("TAGG", list.toString())
                //refreshData()
            }
        })
    }
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val movies: List<Movie>) : MainActivityUiState
}
