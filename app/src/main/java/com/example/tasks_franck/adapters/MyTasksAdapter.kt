package com.example.tasks_franck.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks_franck.R
import com.example.tasks_franck.models.Tasks

class MyTasksAdapter (private val tasks: MutableList<Tasks>) :
    RecyclerView.Adapter<MyTasksAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tvRowTaskName)
        val textViewIsPriority: TextView = itemView.findViewById(R.id.tvRowIsPriority)
        val imgDeleteTask: ImageView = itemView.findViewById(R.id.imgDeleteTask)

        init {
            imgDeleteTask.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    tasks.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.tasks_row_layout, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = tasks[position]
        holder.textViewName.text = currentItem.name
        holder.textViewIsPriority.text = if (currentItem.isPriority) "High Priority" else "Low Priority"
    }

    override fun getItemCount() = tasks.size
}