package com.example.mvvm_redux_mvi_todo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    val title: String,
    var isHighPriority: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}