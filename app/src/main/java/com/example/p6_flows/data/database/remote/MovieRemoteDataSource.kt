package com.example.flows.data.remote


import com.example.p6_flows.data.modelEntity.MovieDescResponse
import com.example.p6_flows.data.modelEntity.toMovie
import com.example.p6_flows.domain.model.Movie
import com.example.p6_flows.utils.NetworkResult
import io.buildwithnd.demotmdb.network.services.MovieService
import javax.inject.Inject

/**
 * fetches data from remote source
 */
class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    BaseApiResponse() {

    suspend fun fetchNowPlayingMovies(): NetworkResult<List<Movie>> {

        return safeApiCall(apiCall = {movieService.getNowPlayingMovies()},
            transform = { nowPlayingMovie -> nowPlayingMovie
                .results?.map { movieEntity ->  movieEntity.toMovie()} ?: emptyList()})

    }

    suspend fun fetchPopularMovies(): NetworkResult<List<Movie>> {

        return safeApiCall(apiCall = {movieService.getPopularMovies()},
            transform = { popularMovieResponse -> popularMovieResponse
                .results?.map { movieEntity ->  movieEntity.toMovie()} ?: emptyList()})

    }

    suspend fun fetchMovie(id: Int): NetworkResult<MovieDescResponse> {

        return safeApiCall(apiCall = {movieService.getMovie(id)})

    }

//    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
//        return try {
//            println("I'm working in thread ${Thread.currentThread().name}")
//            val result = request.invoke()
//            if (result.isSuccessful) {
//                return Result.success(result.body())
//            } else {
//                val errorResponse = ErrorUtils.parseError(result, retrofit)
//                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
//            }
//        } catch (e: Throwable) {
//            Result.error("Unknown Error", null)
//        }
//    }
}