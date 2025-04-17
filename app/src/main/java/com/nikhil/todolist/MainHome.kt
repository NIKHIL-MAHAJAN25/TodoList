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
import androidx.lifecycle.lifecycleScope
import com.nikhil.todolist.database.Maindao
import com.nikhil.todolist.recycler.Inter
import com.nikhil.todolist.recycler.check
import com.nikhil.todolist.recycler.subcheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainHome : AppCompatActivity(),Inter, check,subcheck {
    lateinit var binding: ActivityMainHomeBinding
    lateinit var recycleradapter: Recycleradapter
    var tasklist = arrayListOf<Maintask>()
    var idlist = arrayListOf<Maintask>()
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
        recycleradapter = Recycleradapter(tasklist, this,this)
        binding.Recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.Recycler.adapter = recycleradapter
        getList()
        binding.btnAddtask.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        binding.btnAll.setOnClickListener {
            val alltasks=appdatabase.Maindao().getAllTasks()
            tasklist.clear()
            tasklist.addAll(alltasks)
            recycleradapter.notifyDataSetChanged()
        }

        binding.btnCompleted.setOnClickListener {
            val comptasks = appdatabase.Maindao().getCompletedTasks(true)
            tasklist.clear()
            tasklist.addAll(comptasks)
            recycleradapter.notifyDataSetChanged()
        }
        binding.btnInprogress.setOnClickListener {
            val incomptask=appdatabase.Maindao().getInProgressTasks(false)
            tasklist.clear()
            tasklist.addAll(incomptask)
            recycleradapter.notifyDataSetChanged()
        }

    }

    public fun getList() {
        tasklist.clear()
        tasklist.addAll(appdatabase.Maindao().getAllTasks())
        recycleradapter.notifyDataSetChanged()
    }

    override fun onitemclick(position: Int) {
        val clickedtask = tasklist[position]
        var intent = Intent(this, Subtaskview::class.java)
        intent.putExtra("task_id", clickedtask.maintaskid)
        startActivity(intent)
    }

    override fun onitemlong(position: Int) {
        val taskdelete=tasklist[position].maintaskid
        lifecycleScope.launch(Dispatchers.IO){
            appdatabase.Maindao().deleteMaintask(taskdelete)
            launch(Dispatchers.Main) {
                tasklist.removeAt(position)
                recycleradapter.notifyItemRemoved(position)
            }
        }
    }

    override fun onCheckChanged(position: Int, isChecked: Boolean) {
        val task = tasklist[position]
        task.completed = isChecked
        lifecycleScope.launch(Dispatchers.IO) {
            appdatabase.Maindao().updateTask(task)
        }
    }

    override fun onResume() {
        super.onResume()
        getList()
    }

    override fun onsubcheck(position: Int, isChecked: Boolean) {
        TODO("Not yet implemented")
    }
}

