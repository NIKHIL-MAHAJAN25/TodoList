package com.nikhil.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Maindao {
    @Insert
    suspend fun insertTask(task:Maintask)

    @Query("SELECT * FROM main_task ORDER BY maintaskid DESC")
    fun getAllTasks(): List<Maintask>
    @Query("DELETE FROM main_task WHERE maintaskid = :taskId")
    suspend fun deleteMaintask(taskId: Int)
}