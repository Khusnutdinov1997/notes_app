package com.example.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Класс для создания и настройки базы данных Room. 
// Указываем сущности и версию БД
@Database(
    entities = [Entity::class],
    version = 1
)
abstract class MainDB: RoomDatabase() {
    // Абстрактное свойство для получения доступа к методам Dao
    abstract val dao: Dao

    companion object {
        // Статический метод для создания экземпляра базы данных
        fun createDb(context: Context): MainDB {
            return Room.databaseBuilder(
                context,
                MainDB::class.java, // Класс самой базы данных
                "mainDb"            // Имя файла базы данных в памяти устройства
            ).build()
        }
    }
}
