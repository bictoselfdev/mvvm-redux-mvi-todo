package com.example.libredux

interface Middleware<State> {
    fun invoke(store: Store<State>, action: Action, next: Dispatcher)
}