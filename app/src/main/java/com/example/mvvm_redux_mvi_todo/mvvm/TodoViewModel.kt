package com.example.mvvm_redux_mvi_todo.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_redux_mvi_todo.room.Todo

class TodoViewModel : ViewModel() {

    private var _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> get() = _todoList

    private var items = mutableListOf<Todo>()

    fun insertTodo(content: String) {
        val todo = Todo(content, false)
        TodoRepository.insertTodo(todo)
        items.add(todo)
        _todoList.postValue(items)
    }

    fun updateTodo(position: Int, todo: Todo) {
        TodoRepository.updateTodo(todo)
        items.removeAt(position)
        items.add(position, todo)
        _todoList.postValue(items)
    }

    fun deleteTodo(todo: Todo) {
        TodoRepository.deleteTodo(todo)
        items.remove(todo)
        _todoList.postValue(items)
    }

    suspend fun loadTodo(id: Int): Todo {
        return TodoRepository.getTodo(id)
    }

    fun loadAllTodo(): LiveData<List<Todo>> {
        return TodoRepository.getAllTodoWithLiveData()
    }

    fun deleteAllTodo() {
        TodoRepository.deleteAllTodo()
        items.clear()
        _todoList.postValue(items)
    }
}