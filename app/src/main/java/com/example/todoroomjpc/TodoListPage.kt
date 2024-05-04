package com.example.todoroomjpc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoroomjpc.room.Todo
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(value = inputText, onValueChange = {
                inputText=it
            })
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Text(text = "Add")
            }
        }
        todoList?.let {
            LazyColumn(content = {
                itemsIndexed(it){index, item ->
                    todoItem(item, onDelete = {
                        viewModel.deleteTodo(item.id)
                    })
                }
            })
        }?: Text(text = "No items yet",
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
            )
    }


//    Column (
//        modifi]er = Modifier
//            .fillMaxHeight()
//            .padding(8.dp)
//    ){
//        LazyColumn(content =
//        {
//           itemsIndexed(todoList){index: Int, item: Todo ->
//               Text(text = item.title)
//           }
//        })
//    }
}
@Composable
fun todoItem(item:Todo,onDelete:()->Unit){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = item.title,
                    fontSize = 15.sp,
                    color = Color.White
                    )
                Text(text =SimpleDateFormat("HH:mm:aa, dd/MM", Locale.ENGLISH).format(item.date),
                    fontSize = 15.sp,
                    color = Color.White)
            }
            IconButton(onClick = onDelete) {
                Icon(painter = painterResource(id = R.drawable.baseline_folder_delete_24), contentDescription = "Delete",
                    tint = Color.White
                )
                
            }
        }
}

@Preview(showBackground = true)
@Composable
fun preTodoItem() {
   TodoListPage(viewModel = TodoViewModel())
}