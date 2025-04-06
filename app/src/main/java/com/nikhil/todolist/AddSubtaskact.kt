package com.nikhil.todolist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.nikhil.todolist.database.Appdatabase
import com.nikhil.todolist.database.Maintask
import com.nikhil.todolist.database.Subtask
import com.nikhil.todolist.databinding.ActivityAddSubtaskactBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSubtaskact : AppCompatActivity() {
    lateinit var binding: ActivityAddSubtaskactBinding

    var subtask = arrayListOf<Subtask>()
    lateinit var appdatabase: Appdatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddSubtaskactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appdatabase = Appdatabase.getInstance(this)

        val taskid = intent.getIntExtra("task_id", -1)
        binding.etmainid.setText(taskid.toString())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addsubtask.setOnClickListener {
            val title = binding.mail.text.toString().trim()
            val desc = binding.etdescsub.text.toString().trim()
            val id = binding.etmainid.text
            if (title.isNotEmpty() && desc.isNotEmpty() && id.isNotEmpty()) {
                val newtask =
                    Subtask(title = title, description = desc, mainTaskRefId = taskid)
                lifecycleScope.launch(Dispatchers.IO) {
                    appdatabase.SubDao().insertSubTask(newtask)
                    launch(Dispatchers.Main) {
                        Toast.makeText(this@AddSubtaskact, "Subtask Added!", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }

                }
            } else {
                Toast.makeText(this@AddSubtaskact, "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}