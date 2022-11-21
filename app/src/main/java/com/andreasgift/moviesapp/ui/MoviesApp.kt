package com.andreasgift.moviesapp.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andreasgift.moviesapp.ui.home.MainActivityUiState
import com.andreasgift.moviesapp.NavScreen
import com.andreasgift.moviesapp.ui.detail.DetailScreen
import com.andreasgift.moviesapp.ui.detail.DetailViewModel
import com.andreasgift.moviesapp.ui.home.HomeScreen
import java.util.Collections.emptyList


@Composable
fun MoviesApp(
    navController: NavHostController = rememberNavController(),
    uiState: MainActivityUiState,
    detailViewModel: DetailViewModel
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.6f),
        topBar = {},
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavScreen.Home.route
        ) {
            composable(
                NavScreen.Home.route,
                emptyList()) {
                HomeScreen(uiState = uiState) {
                    navController.navigate("${NavScreen.MovieDetails.route}/$it")
                }
            }
            composable(
                route = NavScreen.MovieDetails.routeWithArgument,
                arguments = listOf(
                    navArgument(NavScreen.MovieDetails.argument0) { type = NavType.IntType }
                )
            ) { backStackEntry ->

                val movieId =
                    backStackEntry.arguments?.getInt(NavScreen.MovieDetails.argument0)
                        ?: return@composable

                DetailScreen(movieId, detailViewModel)
            }
        }
        }
    }



