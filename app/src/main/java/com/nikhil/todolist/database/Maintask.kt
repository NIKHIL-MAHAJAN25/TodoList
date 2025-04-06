package com.nikhil.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="main_task")
data class Maintask (
    @PrimaryKey(autoGenerate = true) val maintaskid:Int=0,
            val title:String,
            val description: String,
            val startDate: String,
            val endDate: String,
)