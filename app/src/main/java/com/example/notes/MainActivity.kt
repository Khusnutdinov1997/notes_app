package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.notes.navigation.AppNavigation
import com.example.notes.ui.theme.NotesTheme

// Главная активность приложения, точка входа
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Включение отображения контента "от края до края" (edge-to-edge)
        enableEdgeToEdge()
        // Установка контента с использованием Jetpack Compose
        setContent {
            // Обертка темы приложения
            NotesTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navHostController = navController
                )
            }
        }
    }
}

