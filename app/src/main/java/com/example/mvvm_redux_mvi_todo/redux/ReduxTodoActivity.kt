package com.example.mvvm_redux_mvi_todo.redux

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libredux.StateSubscriber
import com.example.libredux.Store
import com.example.mvvm_redux_mvi_todo.MainApplication
import com.example.mvvm_redux_mvi_todo.R
import com.example.mvvm_redux_mvi_todo.adapter.TodoAdapter
import com.example.mvvm_redux_mvi_todo.databinding.ActivityTodoReduxBinding
import com.example.mvvm_redux_mvi_todo.redux.action.TodoAction
import com.example.mvvm_redux_mvi_todo.redux.state.AppState
import com.example.mvvm_redux_mvi_todo.room.Todo
import java.util.function.BiConsumer

class ReduxTodoActivity : AppCompatActivity(), StateSubscriber<AppState> {

    private val binding: ActivityTodoReduxBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_todo_redux)
    }

    private val store: Store<AppState> by lazy {
        MainApplication.store
    }

    override fun newState(state: AppState) {
        Log.d(TAG, "newState $state")

        binding.rvResult.adapter = TodoAdapter(state.todo) { position, todo ->
            MainApplication.store.dispatch(TodoAction.Update(position, todo))
        }
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")

        super.onDestroy()
        store.unsubscribe(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)

        initUI()
        setListener()

        store.subscribe(this)
        store.dispatch(TodoAction.LoadAll)
    }

    private fun initUI() {
        binding.rvResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ReduxTodoActivity)
        }
    }

    private fun setListener() {
        binding.btnAdd.setOnClickListener {
            val todoText = binding.etTodo.text.toString()
            if (todoText.isNotEmpty()) {
                binding.etTodo.setText("")
                val todo = Todo(todoText, false)
                store.dispatch(TodoAction.Insert(todo))
            }
        }

        binding.btnDeleteAll.setOnClickListener {
            store.dispatch(TodoAction.DeleteAll)
        }

        binding.btnDelete.setOnClickListener {
            val todo = (binding.rvResult.adapter as TodoAdapter).getSelectedItem()
            if (todo != null) {
                store.dispatch(TodoAction.Delete(todo))
            }
        }
    }

    companion object {
        const val TAG = "ReduxTodoActivity"
    }
}