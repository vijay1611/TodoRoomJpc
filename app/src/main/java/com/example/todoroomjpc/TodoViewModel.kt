package com.example.todoroomjpc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoroomjpc.room.Todo

class TodoViewModel:ViewModel() {

        private var _todoList = MutableLiveData<List<Todo>>()
         val todoList : LiveData<List<Todo>> = _todoList

    fun getAllTodo(){
       _todoList.value = TodoMAnager.getAllTodo().reversed()
    }

    fun addTodo(title: String){
        TodoMAnager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id:Int){
        TodoMAnager.deleteTodo(id)
        getAllTodo()
    }
}