package com.nikhil.todolist.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities=[Maintask::class,Subtask::class],version=1,exportSchema=true)
abstract class Appdatabase:RoomDatabase() {
    abstract fun Maindao():Maindao
    abstract fun SubDao():SubDao
    companion object{
        private var appdatabase:Appdatabase?=null
        fun getInstance(context: Context):Appdatabase{
            if(appdatabase==null)
            {
                appdatabase= Room.databaseBuilder(context, Appdatabase::class.java,
                    "Appdatabase")
                    .allowMainThreadQueries()
                    .build()
            }
            return appdatabase!!
        }
    }

}