package com.nikhil.todolist.recycler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.todolist.R
import com.nikhil.todolist.database.Maintask


class Recycleradapter(var list:List<Maintask>,var clickinterface:Inter):RecyclerView.Adapter<Recycleradapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var title = view.findViewById<TextView>(R.id.taskTitle)
        var description = view.findViewById<TextView>(R.id.taskDescription)
        var start = view.findViewById<TextView>(R.id.taskStartDate)
        var end = view.findViewById<TextView>(R.id.taskEndDate)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {

            title.setText(list[position].title)
            description.setText(list[position].description)
            start.setText("Start:"+list[position].startDate)
            end.setText("End:"+list[position].endDate)
            itemView.setOnClickListener {
                clickinterface.onitemclick(position)
            }
            itemView.setOnLongClickListener {
                clickinterface.onitemlong(position)
                true
            }


        }

    }
}