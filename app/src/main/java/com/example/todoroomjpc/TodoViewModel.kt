package com.example.todoroomjpc

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoroomjpc.room.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel:ViewModel() {

     //   private var _todoList = MutableLiveData<List<Todo>>()

        val todoDao = MainApplication.todoDatabase.getTodoDao()
         val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()

//    fun getAllTodo(){
//       _todoList.value = TodoMAnager.getAllTodo().reversed()
//    }

    fun addTodo(title: String){
//        TodoMAnager.addTodo(title)
//        getAllTodo()
      viewModelScope.launch(Dispatchers.IO) {
          todoDao.insertTodo(Todo(title = title, date = Date.from(Instant.now())))
      }
    }

    fun deleteTodo(id:Int){
//        TodoMAnager.deleteTodo(id)
//        getAllTodo()
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}