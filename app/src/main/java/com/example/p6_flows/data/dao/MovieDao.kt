package com.example.p6_flows.data.dao

import androidx.room.*
import com.example.p6_flows.data.common.QueryConstants
import com.example.p6_flows.data.modelEntity.MovieEntity

@Dao
interface MovieDao {

    @Query(QueryConstants.GET_ALL_MOVIES)
    fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)

    @Delete
    fun delete(movie: MovieEntity)

    @Delete
    fun deleteAll(movie: List<MovieEntity>)
}