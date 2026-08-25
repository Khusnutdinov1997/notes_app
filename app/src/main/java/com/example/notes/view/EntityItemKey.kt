package com.example.notes.view

import com.example.notes.data.Entity

fun entityItemKey(item: Entity): Any {
    return item.id ?: "draft:${item.title}:${item.content}"
}
