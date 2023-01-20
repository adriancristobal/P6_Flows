package com.example.p6_flows.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,


) : Parcelable
