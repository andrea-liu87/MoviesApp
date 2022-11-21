package com.andreasgift.moviesapp.ui.detail

import androidx.lifecycle.ViewModel
import com.andreasgift.moviesapp.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: DatabaseRepository
) : ViewModel() {

    private val movieIdSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow(replay = 1)

    val movieFlow = movieIdSharedFlow.flatMapLatest { movieRepository.getMovieDetailById(it) }

    fun fetchMovieDetailsById(id: Int) = movieIdSharedFlow.tryEmit(id)
}