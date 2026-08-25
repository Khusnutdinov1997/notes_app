package com.example.notes

import android.app.Application
import com.example.notes.data.MainDB

// Класс приложения, расширяющий Application. Служит для глобальных объектов
class App: Application() {
    // Используем lazy делегат для создания базы данных один раз при первом обращении
    val database by lazy {
        MainDB.createDb(this)
    }
}
