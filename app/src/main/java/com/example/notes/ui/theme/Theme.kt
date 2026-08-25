package com.example.notes.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Цветовая схема для темной темы
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    onPrimary = DarkOnPrimaryColor,
    secondary = DarkSecondaryColor,
    onSecondary = DarkOnSecondaryColor,
    background = DarkBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurfaceColor
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
)

// Цветовая схема для светлой темы
private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    onPrimary = LightOnPrimaryColor,
    secondary = LightSecondaryColor,
    onSecondary = LightOnSecondaryColor,
    background = LightBackground,
    surface = LightSurface,
    onSurface = LightOnSurfaceColor
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40
)

@Composable
fun NotesTheme(
    // Определяем, включена ли темная тема в системе
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Динамические цвета доступны начиная с Android 12 (S)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Выбор цветовой схемы в зависимости от версии Android и настроек темы
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            // Использование динамических цветов системы
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        // Если динамические цвета не поддерживаются или выключены
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Применяем выбранную тему Material 3 к контенту
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
