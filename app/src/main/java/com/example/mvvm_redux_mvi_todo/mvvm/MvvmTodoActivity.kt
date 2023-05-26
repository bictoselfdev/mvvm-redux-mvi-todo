package com.example.mvvm_redux_mvi_todo.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_redux_mvi_todo.R
import com.example.mvvm_redux_mvi_todo.adapter.TodoAdapter
import com.example.mvvm_redux_mvi_todo.databinding.ActivityTodoMvvmBinding
import com.example.mvvm_redux_mvi_todo.room.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MvvmTodoActivity : AppCompatActivity() {

    private val binding: ActivityTodoMvvmBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_todo_mvvm)
    }

    private val viewModel : TodoViewModel = TodoViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initUI()

//        CoroutineScope(Dispatchers.IO).launch {
//            val list = ArrayList<Todo>()
//            list.addAll(viewModel.loadAllTodo())
//            binding.rvResult.adapter = TodoAdapter(list) { position, todo ->
//                viewModel.updateTodo(position, todo)
//            }
//        }
    }

    private fun initUI() {
        binding.rvResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MvvmTodoActivity)
        }
    }
}