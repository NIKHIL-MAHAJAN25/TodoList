package com.nikhil.todolist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubDao {
    @Insert
    suspend fun insertSubTask(subtask: Subtask)

    @Query("SELECT * FROM sub_task WHERE mainTaskRefId= :taskId ORDER BY subtaskid DESC")
    fun getSubTasks(taskId: List<Int>): List<Subtask>

    @Query("DELETE FROM sub_task WHERE subtaskid = :subtaskId")
    suspend fun deleteSubtask(subtaskId: Int)
}