package com.example.p6_flows.data.modelEntity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.p6_flows.domain.model.Movie

@Entity(tableName = "MovieEntity")
data class MovieEntity (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val overview: String? = "",
    val popularity: Double = 0.0,

    )

fun MovieEntity.toMovie() : Movie = Movie(this.id, this.title ?: "No title", this.overview ?: "No overview", this.popularity)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(this.id, this.title, this.overview, this.popularity)