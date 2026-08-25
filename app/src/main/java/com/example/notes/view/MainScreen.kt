package com.example.notes.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.navigation.Routes
import com.example.notes.view.companent.ListItem
import com.example.notes.viewModel.MainVIewModel

// Основной экран приложения
@Composable
fun MainScreen(
    // Функция для навигации
    onNavigateTo: (Routes) -> Unit,
    // Инициализация ViewModel для связи с базой данных
    mainViewModel: MainVIewModel
) {
    // Подписываемся на список заметок из БД (превращаем Flow в State для Compose)
    val itemsList by mainViewModel.itemList.collectAsState(initial = emptyList())
    // Используем Box для того чтобы кнопка добавленя была над списком заметок
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        // Основной вертикальный контейнер
        Column(
            modifier = Modifier
                .fillMaxSize()           // Растягиваем на весь экран
                .padding(top = 25.dp, start = 16.dp, end = 16.dp)          // Отступы от краев экрана
        ) {
            // СЕТКА ДЛЯ ЗАМЕТОК (по 2 в ряд)
            //LazyVerticalGrid(
            // Указываем фиксированное количество колонок — 2
            //columns = GridCells.Fixed(2),

            // СТУПЕНЧАТАЯ СЕТКА (Staggered Grid)
            // Она позволяет карточкам разной высоты занимать свободное место
            LazyVerticalStaggeredGrid(
                // Указываем 2 колонки фиксированной ширины
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                    //.weight(1f), // Занимает всё оставшееся пространство экрана
            ) {
                // Отрисовываем элементы из списка
                items(
                    items = itemsList,
                    key = { item -> entityItemKey(item) }
                ) { item->
                    // Отрисовываем каждую заметку с помощью компонента ListItem
                    ListItem(
                        item = item,
                        onClick = {
                            mainViewModel.nameEntity = item
                            mainViewModel.textFildTitleState.value = item.title
                            mainViewModel.textFildContentState.value = item.content
                            onNavigateTo(Routes.AddScreen)
                        },
                        onDelete = {
                            mainViewModel.deleteItem(item)
                        }
                    )
                }
            }

        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(25.dp),
            onClick = {
                // Сбрасывает поля перед создание Новой заметки
                mainViewModel.nameEntity = null
                mainViewModel.textFildTitleState.value = ""
                mainViewModel.textFildContentState.value = ""
                // Переход на Экран добавления заметки
                onNavigateTo(Routes.AddScreen)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        }
    }

}
