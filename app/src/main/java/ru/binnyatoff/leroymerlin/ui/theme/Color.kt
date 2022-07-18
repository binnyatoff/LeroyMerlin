package ru.binnyatoff.leroymerlin.ui.theme

import androidx.compose.ui.graphics.Color

data class Colors(
    val primaryBackground: Color,
    val primaryTintColor: Color,
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val secondaryBackground: Color,
    val actionTextColor: Color,
    val accentColor: Color,
    val minor: Color,
    val primary: Color,
    val buttonMinor:Color,
    val flagSecondary:Color,
    val flagPrimary:Color,
    val buttonAccent:Color,
    val buttonLight: Color

)

val lightPallete = Colors(
    primaryBackground = Color.White,
    primaryTextColor = Color(0xFF333333),
    secondaryTextColor = Color(0xFF666666),
    primaryTintColor = Color(0xFFFF8A00),
    secondaryBackground = Color(0xFFF2F3F5),
    actionTextColor = Color(0xFF065cbf),
    accentColor = Color(0xFF53C43F),
    minor = Color(0xFF999999),
    primary = Color(0xFFE6615E),
    buttonMinor = Color(0xFFE8E9EB),
    flagSecondary = Color(0xFFCED0D6),
    flagPrimary = Color(0xFF5AB030),
    buttonAccent = Color (0xFF53C43F),
    buttonLight = Color (0xFFDEEFD5)
)