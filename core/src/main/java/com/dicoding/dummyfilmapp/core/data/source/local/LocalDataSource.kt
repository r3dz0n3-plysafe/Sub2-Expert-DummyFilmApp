package com.dicoding.dummyfilmapp.core.data.source.local

import com.dicoding.dummyfilmapp.core.data.source.local.entity.MovieEntity
import com.dicoding.dummyfilmapp.core.data.source.local.entity.TvShowEntity
import com.dicoding.dummyfilmapp.core.data.source.local.room.FilmDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val filmDao: FilmDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> =
        filmDao.getAllMovie()

    fun getAllFavMovie(): Flow<List<MovieEntity>> = filmDao.getAllFavMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) = filmDao.insertMovie(movie)

    fun updateFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.favoured = newState
        filmDao.updateMovie(movie)
    }

    fun getAllTvShow(): Flow<List<TvShowEntity>> =
        filmDao.getAllTvShow()

    fun getAllFavTvShow(): Flow<List<TvShowEntity>> = filmDao.getAllFavTvShow()

    suspend fun insertTvShow(tvShow: List<TvShowEntity>) = filmDao.insertTvShow(tvShow)

    fun updateFavTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favoured = newState
        filmDao.updateTvShow(tvShow)
    }
}