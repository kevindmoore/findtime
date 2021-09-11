package com.raywenderlich.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val primaryColor = Color(0xff1976d2)
private val primaryLightColor = Color(0xff63a4ff)
private val secondaryColor = Color(0xff00bfa5)
private val secondaryLightColor = Color(0xff5df2d6)
private val primaryTextColor = Color(0xffffffff)
private val secondaryTextColor = Color(0xff0000000)
private val LightColors = lightColors(
    primary = primaryColor,
    primaryVariant = primaryLightColor,
    onPrimary = Color.White,
    secondary = secondaryColor,
    onSecondary = Color.White
)

private val primaryDarkColor = Color(0xff37474f)
private val secondaryDarkColor = Color(0xff0277bd)
private val DarkColors = darkColors(
    primary = primaryDarkColor,
    primaryVariant = primaryDarkColor,
    onPrimary = Color.White,
    secondary = secondaryDarkColor,
    onSecondary = Color.White
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isSystemInDarkTheme()) DarkColors else LightColors,
        content = {
            Surface(content = content)
        }
    )
}