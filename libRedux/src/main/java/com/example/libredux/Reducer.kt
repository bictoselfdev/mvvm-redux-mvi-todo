package com.example.libredux

interface Reducer<State> {
    fun invoke(action: Action, state: State): State
}