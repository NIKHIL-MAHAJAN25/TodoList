package com.nikhil.todolist.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "sub_task",
    foreignKeys = [ForeignKey(
        entity = Maintask::class,
        parentColumns = ["maintaskid"],
        childColumns = ["mainTaskRefId"],
        onDelete = CASCADE
    )]
)
data class Subtask (
            @PrimaryKey(autoGenerate = true) val subtaskid:Int=0,
            val title: String,
            val description: String?,
            val mainTaskRefId: Int,
            var completed:Boolean?=false
)