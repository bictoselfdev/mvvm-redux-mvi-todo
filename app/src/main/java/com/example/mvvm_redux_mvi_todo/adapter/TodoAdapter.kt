package com.example.mvvm_redux_mvi_todo.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_redux_mvi_todo.databinding.ItemTodoBinding
import com.example.mvvm_redux_mvi_todo.room.Todo
import java.util.function.BiConsumer

class TodoAdapter(private var itemList: ArrayList<Todo>, private var updateAction: BiConsumer<Int, Todo>?) : RecyclerView.Adapter<TodoAdapter.RecyclerViewHolder?>() {

    private lateinit var binding: ItemTodoBinding

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, @SuppressLint("RecyclerView") position: Int) {

        if (selectedPosition == position) holder.itemView.setBackgroundColor(Color.CYAN)
        else holder.itemView.setBackgroundColor(Color.TRANSPARENT)

        if (itemList[position].isHighPriority) {
            binding.tvTitle.setTextColor(Color.RED)
            binding.btnHighPriority.text = "High"
        } else {
            binding.tvTitle.setTextColor(Color.BLACK)
            binding.btnHighPriority.text = "Low"
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position

            notifyDataSetChanged()
        }

        binding.btnHighPriority.setOnClickListener {
            val todo = itemList[position]
            val isHighPriority = !todo.isHighPriority
            todo.isHighPriority = isHighPriority

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                updateAction?.accept(position, todo)
            }

            notifyItemChanged(position)
        }

        holder.updateViewHolder(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class RecyclerViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun updateViewHolder(item: Todo) {

            binding.tvTitle.text = item.title
            if (item.isHighPriority) {
                binding.tvTitle.setTextColor(Color.RED)
                binding.btnHighPriority.text = "High"
            } else {
                binding.tvTitle.setTextColor(Color.BLACK)
                binding.btnHighPriority.text = "Low"
            }
        }
    }

    fun getSelectedItem(): Todo? {
        return if(selectedPosition < itemList.size && selectedPosition != -1) {
            itemList[selectedPosition]
        } else {
            null
        }
    }
}