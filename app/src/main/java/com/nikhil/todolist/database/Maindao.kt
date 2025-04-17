package com.nikhil.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Maindao {
    @Insert
    suspend fun insertTask(task:Maintask)

    @Query("SELECT * FROM main_task ORDER BY maintaskid DESC")
    fun getAllTasks(): List<Maintask>
    @Query("DELETE FROM main_task WHERE maintaskid = :taskId")
    suspend fun deleteMaintask(taskId: Int)
    @Query("SELECT * FROM main_task WHERE completed = :isCompleted")
    fun getCompletedTasks(isCompleted:Boolean): List<Maintask>
    @Update
    fun updateTask(task: Maintask)
    @Query("SELECT * FROM main_task WHERE completed = :isCompleted")
    fun getInProgressTasks(isCompleted: Boolean): List<Maintask>
    @Query("SELECT * FROM main_task WHERE maintaskid = :id")
    fun getTaskById(id: Int): Maintask
}