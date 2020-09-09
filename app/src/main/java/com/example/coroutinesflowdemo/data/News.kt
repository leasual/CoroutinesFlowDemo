package com.example.coroutinesflowdemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey
    val id: Int,
    var title: String,
    var content: String,
    var author: String,
    var imageUrl: String
)