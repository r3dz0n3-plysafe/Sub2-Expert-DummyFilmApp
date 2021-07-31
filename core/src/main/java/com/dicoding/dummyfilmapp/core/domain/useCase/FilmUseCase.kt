package com.dicoding.dummyfilmapp.core.domain.useCase

import com.dicoding.dummyfilmapp.core.data.Resource
import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getAllFavMovie(): Flow<List<Movie>>

    fun updateFavMovie(movie: Movie, state: Boolean)

    fun getAllTvShow(): Flow<Resource<List<TvShow>>>

    fun getAllFavTvShow(): Flow<List<TvShow>>

    fun updateFavTvShow(tvShow: TvShow, state: Boolean)
}