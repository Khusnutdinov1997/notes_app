package com.example.notes.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.notes.App
import com.example.notes.data.Entity
import com.example.notes.data.MainDB
import kotlinx.coroutines.launch

// ViewModel для связи данных и пользовательского интерфейса
class MainVIewModel(
    val mainDb: MainDB // Передаем экземпляр базы данных
): ViewModel() {

    // Поток всех элементов из БД для отображения в списке
    val itemList = mainDb.dao.getAlItems()

    // Состояние текста в текстовом поле ввода
    val textFildTitleState = mutableStateOf("")
    val textFildContentState = mutableStateOf("")

    // Текущая редактируемая заметка (null, если создаем новую)
    var nameEntity: Entity? = null

    // Функция удаления заметки. Запускается в области видимости ViewModel с корутинами
    fun deleteItem(entity: Entity) =
        viewModelScope.launch {
            mainDb.dao.deleteItem(entity)
        }

    // Функция для вставки или обновления заметки (Upsert)
    fun upsertItem() =
        viewModelScope.launch {
            // Если мы редактируем существующую заметку, копируем её с новым текстом
            // Если нет - создаем новый объект Entity
            val entityItem = nameEntity?.copy(title = textFildTitleState.value, content = textFildContentState.value)
                ?: Entity(title = textFildTitleState.value, content = textFildContentState.value)

            // Сохраняем в БД
            mainDb.dao.insertItem(entityItem)

            // Сбрасываем состояние после сохранения
            nameEntity = null
            textFildTitleState.value = ""
            textFildContentState.value = ""
        }
    companion object {
        // Фабрика для создания ViewModel. Нужна, так как мы передаем зависимость в конструктор
        val factory: ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    // Извлекаем базу данных из объекта приложения
                    val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                    return MainVIewModel(database) as T
                }
            }
    }


}
