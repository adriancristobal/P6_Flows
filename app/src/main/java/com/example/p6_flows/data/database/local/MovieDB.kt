package com.example.p6_flows.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.p6_flows.data.dao.MovieDao
import com.example.p6_flows.data.modelEntity.MovieEntity

@Database(entities = [MovieEntity::class],
    version = 1,
    exportSchema = false,
)
//@TypeConverters(GenreConverters::class)
abstract class MovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}