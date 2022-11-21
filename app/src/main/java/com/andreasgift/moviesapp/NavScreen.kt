package com.andreasgift.moviesapp

import androidx.compose.runtime.Immutable

@Immutable
sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object MovieDetails : NavScreen("MovieDetails") {
        const val routeWithArgument: String = "MovieDetails/{movieId}"
        const val argument0: String = "movieId"
    }
}