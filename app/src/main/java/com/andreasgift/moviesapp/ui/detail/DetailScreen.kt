package com.andreasgift.moviesapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.andreasgift.moviesapp.data.model.Movie

@Composable
fun DetailScreen(movieId:Int, viewModel: DetailViewModel) {
    val movie: Movie? by viewModel.movieFlow.collectAsState(initial = null)

    LaunchedEffect(key1 = movieId) {
        viewModel.fetchMovieDetailsById(movieId)
    }
    if (movie == null){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.secondary.copy(0.6f))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(MaterialTheme.colors.secondary),
                shape = RoundedCornerShape(12.dp),
                elevation = 6.dp,
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500" + movie?.posterPath,
                        contentDescription = "poster"
                    )

                    Text(movie?.title ?: "", style = MaterialTheme.typography.h6)

                    Text(
                        text = movie?.releaseDate ?: "",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(text = movie?.overview ?: "", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}