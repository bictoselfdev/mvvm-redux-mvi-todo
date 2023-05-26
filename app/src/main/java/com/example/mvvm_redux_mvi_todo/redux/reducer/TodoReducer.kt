package com.example.mvvm_redux_mvi_todo.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.mvvm_redux_mvi_todo.redux.state.AppState
import com.example.mvvm_redux_mvi_todo.redux.action.TodoAction

class TodoReducer : Reducer<AppState> {

    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        return processReducer(action, state)
    }

    private fun processReducer(action: Action, state: AppState): AppState {
        return when (action) {
            is TodoAction.Insert -> {
                val tempState = state.copy(todo = ArrayList(state.todo))
                tempState.todo.add(action.todo)
                tempState
            }
            is TodoAction.InsertAll -> {
                val tempState = state.copy(todo = ArrayList(state.todo))
                tempState.todo.clear()
                tempState.todo.addAll(action.todoList)
                tempState
            }
            is TodoAction.Update -> {
                val tempState = state.copy(todo = ArrayList(state.todo))
                tempState.todo.removeAt(action.position)
                tempState.todo.add(action.position, action.todo)
                tempState
            }
            is TodoAction.Delete -> {
                val tempState = state.copy(todo = ArrayList(state.todo))
                tempState.todo.remove(action.todo)
                tempState
            }
            is TodoAction.DeleteAll -> {
                val tempState = state.copy(todo = ArrayList(state.todo))
                tempState.todo.clear()
                tempState
            }
            else -> {
                state
            }
        }
    }

    companion object {
        const val TAG = "Todo1Reducer"
    }
}