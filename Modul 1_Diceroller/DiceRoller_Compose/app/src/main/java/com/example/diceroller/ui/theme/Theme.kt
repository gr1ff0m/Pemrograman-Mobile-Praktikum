package com.example.diceroller.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6D4C41), // Warna coklat tua
    onPrimary = Color.White,
    background = Color(0xFFFFF5E1), // Warna krem
    onBackground = Color.Black
)

@Composable
fun DiceRollerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
