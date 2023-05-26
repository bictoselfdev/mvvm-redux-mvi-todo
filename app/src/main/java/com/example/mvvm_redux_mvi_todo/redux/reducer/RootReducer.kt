package com.example.mvvm_redux_mvi_todo.redux.reducer

import android.util.Log
import com.example.libredux.Action
import com.example.libredux.Reducer
import com.example.mvvm_redux_mvi_todo.redux.state.AppState
import com.example.mvvm_redux_mvi_todo.redux.action.TodoAction

class RootReducer : Reducer<AppState> {

    private val todoReducer = TodoReducer()

    override fun invoke(action: Action, state: AppState): AppState {
        Log.d(TAG, action.toString())

        val newState = when (action) {
            is TodoAction -> todoReducer.invoke(action, state)
            else -> state
        }

        return newState
    }

    companion object {
        const val TAG = "RootReducer"
    }
}