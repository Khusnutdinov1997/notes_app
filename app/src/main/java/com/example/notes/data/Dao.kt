package com.example.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// Интерфейс Dao (Data Access Object) для определения методов доступа к базе данных Room
@Dao
interface Dao {

    // Метод для вставки или обновления заметки. Если ID совпадает, данные будут перезаписаны (REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(entity: Entity)

    // Метод для удаления конкретной заметки из базы данных
    @Delete
    suspend fun deleteItem(entity: Entity)

    // Запрос для получения всех заметок из таблицы "entity". 
    // Возвращает Flow, что позволяет автоматически получать обновления при изменении данных
    @Query("SELECT * FROM entity")
    fun getAlItems(): Flow<List<Entity>>
}
