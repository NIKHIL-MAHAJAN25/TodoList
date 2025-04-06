package com.nikhil.todolist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.todolist.R
import com.nikhil.todolist.database.Subtask

class SubrecyclerAdapter(var subtaskList: List<Subtask>,var clickInterface: Inter) : RecyclerView.Adapter<SubrecyclerAdapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.taskTitle)
        var description = view.findViewById<TextView>(R.id.taskDescription)
        var id=view.findViewById<TextView>(R.id.etmainid)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sub_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subtaskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = subtaskList[position].title
            description.text = subtaskList[position].description

            itemView.setOnClickListener {
                clickInterface.onitemclick(position)
            }
            itemView.setOnLongClickListener {
                clickInterface.onitemlong(position)
                true
            }

        }
    }
    fun updateSubtaskList(newList: List<Subtask>) {
        subtaskList = newList
        notifyDataSetChanged()
    }
}