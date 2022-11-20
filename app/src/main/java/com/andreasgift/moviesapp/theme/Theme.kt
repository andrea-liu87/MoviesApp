package com.andreasgift.moviesapp.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//System default
@SuppressLint("ConflictingOnColor")
val LightThemeColorsSystem = lightColors(
    primary = PrimaryMain,
    primaryVariant = RedDark,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = PrimaryMain,
    onBackground = PrimaryMain,
    surface = SurfaceSystem,
    onSurface = Color.Black,
    error = RedDark,
    onError = Color.White
)

@SuppressLint("ConflictingOnColor")
val DarkThemeColorsSystem = darkColors(
    primary = PrimaryMain,
    primaryVariant = RedDark,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = PrimaryMain,
    onBackground = PrimaryMain,
    surface = SurfaceSystem,
    onSurface = Color.Black,
    error = RedDark,
    onError = Color.White
)

@Composable
fun MoviesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColorsSystem
    } else {
        LightThemeColorsSystem
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}