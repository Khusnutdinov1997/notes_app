package com.example.notes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Определение сущности базы данных. Класс представляет собой таблицу "entity"
@Entity(tableName = "entity")
data class Entity(
    // Первичный ключ с автогенерацией (Room сам будет присваивать уникальные ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    // Поле для хранения имени заметки
    val title: String,
    // поле для хранения содержимого заметки
    val content: String
)
