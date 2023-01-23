package com.example.p6_flows.domain.usecases

import com.example.p6_flows.data.repository.MovieRepository
import javax.inject.Inject

class UsecaseGetNowPlayingMovies @Inject constructor(val movieRepository: MovieRepository) {

    suspend fun getNowPlayingMovies() {
        movieRepository.fetchNowPlayingMovies()
    }
}