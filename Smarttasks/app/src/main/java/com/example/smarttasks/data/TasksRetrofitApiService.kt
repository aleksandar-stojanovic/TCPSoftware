package com.example.smarttasks.data

import com.example.smarttasks.model.TasksModel
import retrofit2.http.GET

interface TasksRetrofitApiService {
    @GET("tasks")
    suspend fun getAllTasks(): TasksModel
}