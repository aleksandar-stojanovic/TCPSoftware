package com.example.smarttasks.data

import com.example.smarttasks.model.Task
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class TasksRepository {

    suspend fun getAllTasksFromJSON(): ArrayList<Task> {
        val gson = GsonBuilder().registerTypeAdapter(
            LocalDate::class.java,
            JsonDeserializer { json, type, jsonDeserializationContext ->
                    LocalDate.parse(json.asJsonPrimitive.asString)
            })
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo9094133.mockable.io/")
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(TasksRetrofitApiService::class.java).getAllTasks().tasks
    }

}