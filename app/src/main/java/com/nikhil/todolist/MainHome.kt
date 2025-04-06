package com.nikhil.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.todolist.database.Appdatabase
import com.nikhil.todolist.database.Maintask
import com.nikhil.todolist.databinding.ActivityMainHomeBinding
import com.nikhil.todolist.recycler.Recycleradapter
import androidx.lifecycle.Observer
import com.nikhil.todolist.database.Maindao
import com.nikhil.todolist.recycler.Inter

class MainHome : AppCompatActivity(),Inter {
    lateinit var binding: ActivityMainHomeBinding
    lateinit var recycleradapter: Recycleradapter
    var tasklist = arrayListOf<Maintask>()
    var idlist= arrayListOf<Maintask>()
    lateinit var appdatabase: Appdatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appdatabase = Appdatabase.getInstance(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recycleradapter = Recycleradapter(tasklist,this)
        binding.Recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.Recycler.adapter = recycleradapter
        getList()
        binding.btnAddtask.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        getList()

    }

    public fun getList() {
        tasklist.clear()
        tasklist.addAll(appdatabase.Maindao().getAllTasks())
        recycleradapter.notifyDataSetChanged()
        }

    override fun onitemclick(position: Int) {
        val clickedtask=tasklist[position]
        var intent=Intent(this,Subtaskview::class.java)
        intent.putExtra("task_id",clickedtask.maintaskid)
        startActivity(intent)
    }

    override fun onitemlong(position: Int) {
        TODO("Not yet implemented")
    }
    override fun onResume() {
        super.onResume()
        getList()
    }

}