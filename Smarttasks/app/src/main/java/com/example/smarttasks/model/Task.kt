package com.example.smarttasks.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class Task(
    @SerializedName("id")
    val id : String,
    @SerializedName("TargetDate")
    val targetDate : LocalDate,
    @SerializedName("DueDate")
    val dueDate : LocalDate?,
    @SerializedName("Title")
    val title : String,
    @SerializedName("Description")
    val description : String,
    @SerializedName("Priority")
    val priority : Int
)