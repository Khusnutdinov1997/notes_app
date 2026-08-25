package com.example.notes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
//
//// Настройка типографики приложения (шрифты и стили текста)
//val Typography = Typography(
//    // Стандартный стиль для крупного основного текста
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default, // Стандартный системный шрифт
//        fontWeight = FontWeight.Normal, // Обычная толщина
//        fontSize = 16.sp,              // Размер 16 sp
//        lineHeight = 24.sp,            // Межстрочный интервал
//        letterSpacing = 0.5.sp         // Межсимвольный интервал
//    )
//)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    )
)
