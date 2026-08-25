package com.example.notes.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.notes.viewModel.MainVIewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    onBack: () -> Unit,
    mainViewModel: MainVIewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 25.dp, start = 16.dp, end = 16.dp)
    ) {
        //поле воода заголовка
        TextField(
            modifier = Modifier.fillMaxWidth(),
            // привязывает значени введенного текста к состоянию во ViewModel
            value = mainViewModel.textFildTitleState.value,
            // Обновление текста во ViewModel при вводе
            onValueChange = { inputText ->
                mainViewModel.textFildTitleState.value = inputText
            },
            label = { Text("Title") },
            shape = RoundedCornerShape(15.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,   // Убираем линию снизу при фокусе
                unfocusedIndicatorColor = Color.Transparent, // Убираем линию снизу без фокуса
                disabledIndicatorColor = Color.Transparent   // Убираем линию для выключенного поля
            )
        )
        // Поле ввода текста заметки
        TextField(
            modifier = Modifier
                .fillMaxWidth() // Поле занимает всю доступную ширину
                .padding(top = 8.dp)
                .weight(1f),
            // Привязываем значение текста к состоянию во ViewModel
            value = mainViewModel.textFildContentState.value,
            // Обновляем текст во ViewModel при вводе
            onValueChange = { inputText ->
                mainViewModel.textFildContentState.value = inputText // Обновляем состояние
            }, // Обработчик изменения текста
            label = { Text("Text") }, // Текст-подсказка
            // Закругляем все углы поля ввода
            shape = RoundedCornerShape(15.dp),
            // Настройка цветов поля ввода
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF1F1F1),          // Светло-серый фон
                focusedIndicatorColor = Color.Transparent,   // Убираем линию снизу при фокусе
                unfocusedIndicatorColor = Color.Transparent, // Убираем линию снизу без фокуса
                disabledIndicatorColor = Color.Transparent   // Убираем линию для выключенного поля
            )
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            onClick = {
                mainViewModel.upsertItem() // сохранение заметки
                onBack() // выход из экрана добавления
            }
        ) {
            Text(text = "Save")
        }
    }
}