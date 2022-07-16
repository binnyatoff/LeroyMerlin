package ru.binnyatoff.leroymerlin.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackground: Color,
    val primaryTintColor: Color,
    val primaryTextColor: Color,
    val secondaryBackground: Color,
    val actionTextColor: Color,
    val accentColor: Color,
    val minor: Color,
    val primary: Color

)

val lightPallete = Colors(
    primaryBackground = Color.White,
    primaryTextColor = Color(0xFF000000),
    primaryTintColor = Color(0xFFFF8A00),
    secondaryBackground = Color(0x10D0CCC7),
    actionTextColor = Color(0xFF065cbf),
    accentColor = Color(0xFF53C43F),
    minor = Color(0xFF999999),
    primary = Color(0xFFE6615E)
)