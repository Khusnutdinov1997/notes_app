package com.example.notes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.notes.view.AddScreen
import com.example.notes.view.MainScreen
import com.example.notes.viewModel.MainVIewModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object MainScreen : Routes
    @Serializable
    data object AddScreen : Routes
}

@Composable
fun AppNavigation(
    navHostController: NavHostController,
) {
    // создаем ViewModel, чтобы она была общая для всех экранов
    val mainVIewModel: MainVIewModel = viewModel(factory = MainVIewModel.factory)
    NavHost(
        navController = navHostController,
        // Стартовый экран навигации
        startDestination = Routes.MainScreen
    ) {
        // Описание первого маршрута: Главный экран
        composable<Routes.MainScreen> {
            MainScreen(
                mainViewModel = mainVIewModel,
                // Вызов функции навигации
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }
        // Описание воторого маршрута: Экран добавления заметки и редактирования
        composable<Routes.AddScreen> {
            AddScreen(
                mainViewModel = mainVIewModel,
                // Вызов функции навигации назад
                onBack = {navHostController.popBackStack()}
            )
        }
    }
}