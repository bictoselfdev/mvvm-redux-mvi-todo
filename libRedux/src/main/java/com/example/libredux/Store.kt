package com.example.libredux

import java.util.concurrent.CopyOnWriteArrayList

class Store<State>(reducer: Reducer<State>, middleware: Middleware<State>, initState: State) {
    private val dispatcher: Dispatcher
    private var subscriptions: MutableList<StateSubscriber<State>> = CopyOnWriteArrayList()

    var state: State = initState
        private set(value) {
            field = value
            subscriptions.forEach { subscriber -> subscriber.newState(field) }
        }

    init {
        val reducerDispatcher = object : Dispatcher {
            @Synchronized
            override fun dispatch(action: Action) {
                println("[Store] Reducer Logic Start : current state($state)")
                val newState = reducer.invoke(action, state)
                println("[Store] Reducer Logic Completed : new state($newState)")
                if (state != newState) {
                    println("[Store] State is Changed!")
                    state = newState
                }
            }
        }

        val middlewareDispatcher = object : Dispatcher {
            override fun dispatch(action: Action) {
                println("[Store] Middleware Logic Start : $action")
                middleware.invoke(this@Store, action, reducerDispatcher)
            }
        }

        dispatcher = middlewareDispatcher
    }

    fun dispatch(action: Action) {
        println("[Store] Action Dispatch : $action")
        dispatcher.dispatch(action)
    }

    fun subscribe(subscriber: StateSubscriber<State>) {
        subscriptions.add(subscriber)
        subscriber.newState(state)
    }

    fun unsubscribe(subscriber: StateSubscriber<State>) {
        subscriptions.remove(subscriber)
    }
}
