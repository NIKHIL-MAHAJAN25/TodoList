package com.nikhil.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.todolist.database.Appdatabase
import com.nikhil.todolist.database.Maintask
import com.nikhil.todolist.database.Subtask
import com.nikhil.todolist.databinding.ActivitySubtaskviewBinding
import com.nikhil.todolist.recycler.Inter
import com.nikhil.todolist.recycler.Recycleradapter
import com.nikhil.todolist.recycler.SubrecyclerAdapter
import com.nikhil.todolist.recycler.subcheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Subtaskview : AppCompatActivity(), Inter,subcheck {
    lateinit var binding: ActivitySubtaskviewBinding
    lateinit var subrecyclerAdapter: SubrecyclerAdapter
    var tasklist = arrayListOf<Subtask>()
    private var selected: Int = -1
    lateinit var appdatabase: Appdatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySubtaskviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appdatabase = Appdatabase.getInstance(this)
        selected = intent.getIntExtra("task_id", -1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        subrecyclerAdapter = SubrecyclerAdapter(tasklist, this, this)
        binding.Recycler1.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.Recycler1.adapter = subrecyclerAdapter
        getList()
        binding.btnAddtask2.setOnClickListener {
            val intent = Intent(this, AddSubtaskact::class.java)
            intent.putExtra("task_id", selected)
            startActivity(intent)
        }


    }

    override fun onitemclick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onitemlong(position: Int) {
        val delete = tasklist[position].subtaskid
        lifecycleScope.launch(Dispatchers.IO) {
            appdatabase.SubDao().deleteSubtask(delete)
            launch(Dispatchers.Main) {
                tasklist.removeAt(position)
                subrecyclerAdapter.notifyItemRemoved(position)
            }
        }
    }


    public fun getList() {
        tasklist.clear()
        tasklist.addAll(appdatabase.SubDao().getSubTasks(listOf(selected)))
        subrecyclerAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getList()
    }

    override fun onsubcheck(position: Int, isChecked: Boolean) {
        tasklist[position].completed = isChecked
        lifecycleScope.launch(Dispatchers.IO) {

            appdatabase.SubDao().updateTask(tasklist[position])


            val allSubtasks = appdatabase.SubDao().getSubTasks(listOf(selected))
            val allCompleted = allSubtasks.all { it.completed == true }
            val mainTask = appdatabase.Maindao().getTaskById(selected)
            mainTask.completed = allCompleted
            appdatabase.Maindao().updateTask(mainTask)
        }
    }
}