package com.example.mvvm_redux_mvi_todo.redux.middleware

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Dispatcher
import com.example.libredux.Middleware
import com.example.libredux.Store
import com.example.mvvm_redux_mvi_todo.MainApplication
import com.example.mvvm_redux_mvi_todo.redux.action.TodoAction
import com.example.mvvm_redux_mvi_todo.redux.state.AppState
import com.example.mvvm_redux_mvi_todo.room.TodoDataBase
import kotlinx.coroutines.*

class AppMiddleware : Middleware<AppState> {

    private val todoDataBase: TodoDataBase by lazy {
        TodoDataBase.getDatabase(MainApplication.getContext())
    }
    private val coroutineScopeIO = CoroutineScope(Dispatchers.IO)
    private val coroutineScopeMain = CoroutineScope(Dispatchers.Main)

    override fun invoke(store: Store<AppState>, action: Action, next: Dispatcher) {
        Log.d(TAG, action.toString())

        coroutineScopeMain.launch {
            val newAction = processAsync(action).await()
            next.dispatch(newAction)
        }
    }

    private fun processAsync(action: Action): Deferred<Action> {
        return coroutineScopeIO.async {
            var newAction = action
            when (action) {
                is TodoAction.Insert -> {
                    todoDataBase.todoDao().insert(action.todo)
                }
                is TodoAction.InsertAll -> {
                    todoDataBase.todoDao().insertAll(action.todoList)
                }
                is TodoAction.Update -> {
                    todoDataBase.todoDao().update(action.todo)
                }
                is TodoAction.Delete -> {
                    todoDataBase.todoDao().delete(action.todo)
                }
                is TodoAction.LoadAll -> {
                    val todoList = todoDataBase.todoDao().getAll()
                    newAction = TodoAction.InsertAll(todoList)
                }
                is TodoAction.DeleteAll -> {
                    todoDataBase.todoDao().deleteAll()
                }
                else -> {

                }
            }
            newAction
        }
    }

    companion object {
        const val TAG = "AppMiddleware"
    }
}