package io.buildwithnd.demotmdb.network.services

import com.example.flows.data.modelo.PopularMovieResponse
import com.example.p6_flows.data.modelEntity.MovieDescResponse
import com.example.p6_flows.data.modelEntity.NowPlayingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API Service
 */
interface MovieService {


    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies() : Response<NowPlayingMovieResponse>
    @GET("/3/movie/popular")
    suspend fun getPopularMovies() : Response<PopularMovieResponse>
    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDescResponse>
}