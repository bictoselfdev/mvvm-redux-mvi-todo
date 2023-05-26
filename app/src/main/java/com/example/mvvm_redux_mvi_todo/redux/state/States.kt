package com.example.mvvm_redux_mvi_todo.redux.state

import com.example.mvvm_redux_mvi_todo.room.Todo

data class AppState(
    var todo: ArrayList<Todo> = ArrayList()
)