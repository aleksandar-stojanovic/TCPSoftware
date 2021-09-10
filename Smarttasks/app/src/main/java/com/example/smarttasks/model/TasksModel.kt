package com.example.smarttasks.model

import com.google.gson.annotations.SerializedName

data class TasksModel(
    @SerializedName("tasks") val tasks : ArrayList<Task>
)