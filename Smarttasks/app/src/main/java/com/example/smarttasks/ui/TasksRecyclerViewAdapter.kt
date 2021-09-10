package com.example.smarttasks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smarttasks.R
import com.example.smarttasks.model.Task
import java.time.temporal.ChronoUnit

class TasksRecyclerViewAdapter(private val taskList: ArrayList<Task>?) :
    RecyclerView.Adapter<TasksRecyclerViewAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskTitle: TextView = itemView.findViewById(R.id.tv_item_tasktitle)
        val tvDueDate: TextView = itemView.findViewById(R.id.tv_item_duedate)
        val tvDaysLeft: TextView = itemView.findViewById(R.id.tv_item_daysleft)

        init {
            // Define click listener for the status of Task
        }

        fun bind(task: Task) {
            tvTaskTitle.text = task.title
            tvDueDate.text = task.dueDate?.toString() ?: "-"
            tvDaysLeft.text = if (task.dueDate != null)
                ChronoUnit.DAYS.between(task.targetDate, task.dueDate).toString()
            else
                "-"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList!!.get(position))
    }

    override fun getItemCount(): Int = taskList!!.size
}