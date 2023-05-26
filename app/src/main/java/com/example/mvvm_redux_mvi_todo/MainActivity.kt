package com.example.mvvm_redux_mvi_todo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvm_redux_mvi_todo.databinding.ActivityMainBinding
import com.example.mvvm_redux_mvi_todo.mvi.MviTodoActivity
import com.example.mvvm_redux_mvi_todo.mvvm.MvvmTodoActivity
import com.example.mvvm_redux_mvi_todo.redux.ReduxTodoActivity

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setListener()
    }

    private fun setListener() {
        binding.btnMVVM.setOnClickListener {
            startActivity(Intent(this@MainActivity, MvvmTodoActivity::class.java))
        }

        binding.btnRedux.setOnClickListener {
            startActivity(Intent(this@MainActivity, ReduxTodoActivity::class.java))
        }

        binding.btnMVI.setOnClickListener {
            startActivity(Intent(this@MainActivity, MviTodoActivity::class.java))
        }
    }
}