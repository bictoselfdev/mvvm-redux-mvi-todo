package com.example.libredux

interface StateSubscriber<State> {
    fun newState(state: State)
}