package com.example.p6_flows.data.repository

import com.example.flows.data.remote.MovieRemoteDataSource
import com.example.p6_flows.data.dao.MovieDao
import com.example.p6_flows.data.modelEntity.MovieDescResponse
import com.example.p6_flows.data.modelEntity.toMovie
import com.example.p6_flows.data.modelEntity.toMovieEntity
import com.example.p6_flows.domain.model.Movie
import com.example.p6_flows.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao
) {

    fun fetchNowPlayingMovies(): Flow<NetworkResult<List<Movie>>> {
        return flow {
            emit(fetchNowPlayingMoviesCached())
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchNowPlayingMovies()
            emit(result)
            //Cache to database if response is successful
            if (result is NetworkResult.Success) {
                result.data?.let { it ->
                    movieDao.deleteAll(it.map { it.toMovieEntity() })
                    movieDao.insertAll(it.map { it.toMovieEntity() })
                }
            }

        }.flowOn(Dispatchers.IO)
    }

    private fun fetchNowPlayingMoviesCached(): NetworkResult<List<Movie>> =
        movieDao.getAll().let { list ->
            NetworkResult.Success(list.map { it.toMovie() })
        }

    fun fetchMovie(id: Int): Flow<NetworkResult<MovieDescResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(movieRemoteDataSource.fetchMovie(id))
        }.flowOn(Dispatchers.IO)
    }
}