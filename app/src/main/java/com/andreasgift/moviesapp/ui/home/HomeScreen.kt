package com.andreasgift.moviesapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    uiState: MainActivityUiState,
    navigateToDetail: (Int) -> Unit
) {
    if (uiState is MainActivityUiState.Success) {
        val list = uiState.movies
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(items = list) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.secondary)
                        .clickable { navigateToDetail(movie.id ?: 0) },
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.secondary
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500" + movie.posterPath,
                            contentDescription = "poster"
                        )
                        Column() {
                            Text(movie.title ?: "", style = MaterialTheme.typography.subtitle1)
                            Text(
                                text = movie.releaseDate ?: "",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}