package com.example.mvvm_redux_mvi_todo.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvm_redux_mvi_todo.R
import com.example.mvvm_redux_mvi_todo.databinding.ActivityTodoMviBinding

class MviTodoActivity : AppCompatActivity() {

    private val binding: ActivityTodoMviBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_todo_mvi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}