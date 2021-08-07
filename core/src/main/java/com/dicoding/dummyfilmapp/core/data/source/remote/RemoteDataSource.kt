package com.dicoding.dummyfilmapp.core.data.source.remote

import android.util.Log
import com.dicoding.dummyfilmapp.core.data.source.remote.network.ApiResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.network.RetrofitEndPoint
import com.dicoding.dummyfilmapp.core.data.source.remote.response.movie.MovieResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.dummyfilmapp.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val retrofitEndPoint: RetrofitEndPoint) {

    companion object {
        const val TAG = "Remote Data Source"
    }

    fun getMovieList(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                EspressoIdlingResource.increment()
                val response = retrofitEndPoint.getMovieList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getTvShowList(): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                EspressoIdlingResource.increment()
                val response = retrofitEndPoint.getTvShowList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e: Exception) {
                EspressoIdlingResource.decrement()
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}