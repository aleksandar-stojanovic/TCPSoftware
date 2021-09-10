package com.example.smarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smarttasks.data.TasksRepository

class TasksViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TasksViewModel(TasksRepository()) as T
    }
}