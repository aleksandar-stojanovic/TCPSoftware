package com.example.smarttasks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.smarttasks.R
import com.example.smarttasks.model.Task
import com.example.smarttasks.viewmodel.TasksViewModel
import com.example.smarttasks.viewmodel.TasksViewModelFactory

class MainActivity : AppCompatActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.hide()
        val tvDayTitle = toolbar.findViewById<TextView>(R.id.toolbar_title)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val tvNoTasksForToday: TextView = findViewById(R.id.tvNoTasksForToday)
        val imgNoTasksSmile: ImageView = findViewById(R.id.imgNoTasksSmile)

        val tasksViewModel =
            ViewModelProvider(this, TasksViewModelFactory()).get(TasksViewModel::class.java)
        tasksViewModel.getTasksForRequestedDay().observe(this, Observer<ArrayList<Task>> { it ->
            if (it.isEmpty()) {
                recyclerView.visibility = View.GONE
                tvNoTasksForToday.visibility = View.VISIBLE
                imgNoTasksSmile.visibility = View.VISIBLE
            } else {
                tvDayTitle.text = tasksViewModel.dayForTitle.toString()
                recyclerView.visibility = View.VISIBLE
                tvNoTasksForToday.visibility = View.GONE
                imgNoTasksSmile.visibility = View.GONE
                recyclerView.adapter = TasksRecyclerViewAdapter(it)
            }
        })
        findViewById<ImageView>(R.id.imgButtonLeft).setOnClickListener { l ->
            tasksViewModel.getTasksForRequestedDay("previous")
                .observe(this, Observer<ArrayList<Task>> { it ->
                    if (it.isEmpty()) {
                        recyclerView.visibility = View.GONE
                        tvNoTasksForToday.visibility = View.VISIBLE
                        imgNoTasksSmile.visibility = View.VISIBLE
                    } else {
                        tvDayTitle.text = tasksViewModel.dayForTitle.toString()
                        recyclerView.visibility = View.VISIBLE
                        tvNoTasksForToday.visibility = View.GONE
                        imgNoTasksSmile.visibility = View.GONE
                        recyclerView.adapter = TasksRecyclerViewAdapter(it)
                    }
                })
        }
        findViewById<ImageView>(R.id.imgButtonRight).setOnClickListener { l ->
            tasksViewModel.getTasksForRequestedDay("next")
                .observe(this, Observer<ArrayList<Task>> { it ->
                    if (it.isEmpty()) {
                        recyclerView.visibility = View.GONE
                        tvNoTasksForToday.visibility = View.VISIBLE
                        imgNoTasksSmile.visibility = View.VISIBLE
                    } else {
                        tvDayTitle.text = tasksViewModel.dayForTitle.toString()
                        recyclerView.visibility = View.VISIBLE
                        tvNoTasksForToday.visibility = View.GONE
                        imgNoTasksSmile.visibility = View.GONE
                        recyclerView.adapter = TasksRecyclerViewAdapter(it)
                    }
                })
        }
    }
}