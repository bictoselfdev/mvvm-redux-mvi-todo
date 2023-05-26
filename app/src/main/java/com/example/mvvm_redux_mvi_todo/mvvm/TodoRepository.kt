package com.example.mvvm_redux_mvi_todo.mvvm

import androidx.lifecycle.LiveData
import com.example.mvvm_redux_mvi_todo.MainApplication
import com.example.mvvm_redux_mvi_todo.room.Todo
import com.example.mvvm_redux_mvi_todo.room.TodoDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object TodoRepository {
    private val todoDataBase = TodoDataBase.getDatabase(MainApplication.getContext())
    private val coroutineScopeIO = CoroutineScope(Dispatchers.IO)

    fun insertTodo(todo: Todo) {
        coroutineScopeIO.launch {
            todoDataBase.todoDao().insert(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        coroutineScopeIO.launch {
            todoDataBase.todoDao().update(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        coroutineScopeIO.launch {
            todoDataBase.todoDao().delete(todo)
        }
    }

    suspend fun getTodo(id: Int): Todo {
        return withContext(coroutineScopeIO.coroutineContext) {
            todoDataBase.todoDao().get(id)
        }
    }

    suspend fun getAllTodo(): List<Todo> {
        return withContext(coroutineScopeIO.coroutineContext) {
            todoDataBase.todoDao().getAll()
        }
    }

    fun getAllTodoWithLiveData(): LiveData<List<Todo>> {
        return todoDataBase.todoDao().getAllWithLiveData()
    }

    fun deleteAllTodo() {
        coroutineScopeIO.launch {
            todoDataBase.todoDao().deleteAll()
        }
    }
}