package com.example.smarttasks.viewmodel

import androidx.lifecycle.*
import com.example.smarttasks.data.TasksRepository
import com.example.smarttasks.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.collections.ArrayList

class TasksViewModel(private val tasksRepository: TasksRepository) : ViewModel() {
    private val _tasksList = MutableLiveData<ArrayList<Task>>()
    var tasksArrayList = ArrayList<Task>()
    val filteredTasksList = ArrayList<Task>()
    var dayForTitle: LocalDate

    init {
        dayForTitle = LocalDate.now()
        viewModelScope.launch(Dispatchers.IO) {
            tasksArrayList.clear()
            tasksArrayList = tasksRepository.getAllTasksFromJSON()
        }
    }

    fun getTasksForRequestedDay(day: String = ""): LiveData<ArrayList<Task>> {
        if (day == "previous") {
            dayForTitle = dayForTitle.minusDays(1)
        } else if (day == "next") {
            dayForTitle = dayForTitle.plusDays(1)
        }
        viewModelScope.launch(Dispatchers.Default) {
            filteredTasksList.clear()
            tasksArrayList.forEach { task ->
                if (task.dueDate != null) {
                    if (task.targetDate.isBefore(dayForTitle) &&
                        dayForTitle.isBefore(task.dueDate))
                        filteredTasksList.add(task)
                } else {
                    if (task.targetDate.isBefore(dayForTitle))
                        filteredTasksList.add(task)
                }
            }
            filteredTasksList.sortWith(compareByDescending<Task> { it.priority }.thenBy { it.targetDate } )
            _tasksList.postValue(filteredTasksList)
        }
        return _tasksList
    }

}