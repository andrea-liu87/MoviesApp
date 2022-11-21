package com.andreasgift.moviesapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(MaterialTheme.colors.secondary),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp,
            backgroundColor = MaterialTheme.colors.secondary
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500" + movie?.posterPath,
                    contentDescription = "poster"
                )

                Text(movie?.title ?: "", style = MaterialTheme.typography.subtitle1)

                Text(
                    text = movie?.releaseDate ?: "",
                    style = MaterialTheme.typography.body2
                )
                Text(text = movie?.overview ?: "", style = MaterialTheme.typography.body1)
            }
        }
}