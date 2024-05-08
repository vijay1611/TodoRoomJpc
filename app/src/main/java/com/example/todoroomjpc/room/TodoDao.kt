package com.example.todoroomjpc.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao {
    @Query("SELECT * FROM TODO_TABLE")
    fun getAllTodo():LiveData<List<Todo>>
    @Insert
    fun insertTodo(todo: Todo)
    @Query("Delete from todo_table where id = :id")
    fun deleteTodo(id:Int)
}