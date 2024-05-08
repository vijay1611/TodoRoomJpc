package com.example.todoroomjpc.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String,
    var date:Date
)
