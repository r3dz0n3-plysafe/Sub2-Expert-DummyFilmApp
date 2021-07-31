package com.dicoding.dummyfilmapp.core.data.source.remote.network

import com.dicoding.dummyfilmapp.core.BuildConfig
import com.dicoding.dummyfilmapp.core.data.source.remote.response.movie.MovieListResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow.TvShowListResponse
import retrofit2.http.GET

interface RetrofitEndPoint {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getMovieList(): MovieListResponse

    @GET("tv/top_rated?api_key=$API_KEY")
    suspend fun getTvShowList(): TvShowListResponse
}