package com.example.coroutinesflowdemo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutinesflowdemo.data.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews() : Flow<List<News>>

    @Query("SELECT * FROM news WHERE id = :id")
    fun getNewsById(id: Int): Flow<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: News)
}