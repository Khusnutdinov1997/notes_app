package com.example.notes.view.companent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notes.data.Entity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    item: Entity,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    // Состояние SwipeToDismissBox
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            //  если состояние SwipeToDismissBox равно SwipeToDismissBoxValue.EndToStart,
            //  то удаляем элемент
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true // подтверждение удаления
            } else {
                false
            }
        },
        // параметр котрый указывает на сколько надо свдвинуть чтобы удаление засчиталось
        positionalThreshold = { distance ->
            distance * 0.7f
        }
    )
    // компанент для создания удаления через свайп
    SwipeToDismissBox(
        // состояние SwipeToDismissBox
        state = dismissState,
        // отключение свайпа
        enableDismissFromStartToEnd = false,
        // отображение при свайпе
        backgroundContent = {
            // для иконки удаления
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .background(
                        color = Color.Red,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                )
            }
        },
        // основной контет заметки
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        onClick()
                    }
            ) {
                // Column располагает тексты вертикально (друг под другом)
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    // Заголовок заметки
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1, // Заголовок в одну строку
                        overflow = TextOverflow.Ellipsis // Если не влезает — будет "..."
                    )

                    // Текст заметки (под заголовком)
                    Text(
                        text = item.content,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp), // Отступ от заголовка
                        maxLines = 10, // Ограничим, чтобы карточка не была бесконечной
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }
    )
}
