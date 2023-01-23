package com.example.p6_flows.ui.fragment.main

import com.example.p6_flows.domain.model.Movie

interface MainContract {

    sealed class Event {

        object GetData : Event()
        object ShowMessage: Event()

    }

    data class State(
        val movies: List<Movie> = emptyList(),
        val isLoading : Boolean = false,
        val error: String? = null,

        )
}