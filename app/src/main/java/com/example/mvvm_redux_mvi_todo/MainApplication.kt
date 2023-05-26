package com.example.mvvm_redux_mvi_todo

import android.app.Application
import android.content.Context
import com.example.libredux.Store
import com.example.mvvm_redux_mvi_todo.redux.state.AppState
import com.example.mvvm_redux_mvi_todo.redux.middleware.AppMiddleware
import com.example.mvvm_redux_mvi_todo.redux.reducer.RootReducer

class MainApplication : Application() {

    init {
        instance = this
        rootReducer = RootReducer()
        appMiddleware = AppMiddleware()
        store = Store(rootReducer, appMiddleware, AppState())
    }

    companion object {
        lateinit var instance: MainApplication
        lateinit var store: Store<AppState>
        lateinit var rootReducer: RootReducer
        lateinit var appMiddleware: AppMiddleware

        fun getContext(): Context {
            return instance.applicationContext
        }
    }
}