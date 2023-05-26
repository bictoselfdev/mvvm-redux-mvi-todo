package com.example.mvvm_redux_mvi_todo.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Insert
    suspend fun insertAll(todo: List<Todo>)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()

    @Query("SELECT * from todo WHERE id = :id")
    suspend fun get(id: Int): Todo

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<Todo>

    @Query("SELECT * FROM todo")
    fun getAllWithLiveData(): LiveData<List<Todo>>
}