package com.dicoding.dummyfilmapp.viewmodel

import androidx.lifecycle.ViewModel
import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow
import com.dicoding.dummyfilmapp.core.domain.useCase.FilmUseCase

class DetailFilmViewModel(private val filmUseCase: FilmUseCase) :
    ViewModel() {
    fun setMovieFavourite(movie: Movie, newState: Boolean) =
        filmUseCase.updateFavMovie(movie, newState)

    fun setTvShowFavourite(tvShow: TvShow, newState: Boolean) =
        filmUseCase.updateFavTvShow(tvShow, newState)
}