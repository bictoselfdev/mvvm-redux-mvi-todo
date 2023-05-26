package com.example.mvvm_redux_mvi_todo.redux.action

import com.example.libredux.Action
import com.example.mvvm_redux_mvi_todo.room.Todo

sealed class TodoAction : Action {
    data class Insert(val todo: Todo) : TodoAction()
    data class InsertAll(val todoList: List<Todo>) : TodoAction()
    data class Update(val position: Int, val todo: Todo) : TodoAction()
    data class Delete(val todo: Todo) : TodoAction()
    object LoadAll : TodoAction()
    object DeleteAll : TodoAction()
}
