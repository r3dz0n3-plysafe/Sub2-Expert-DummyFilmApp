package com.dicoding.dummyfilmapp.core.domain.useCase

import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow
import com.dicoding.dummyfilmapp.core.domain.repository.IFilmRepository

class FilmInteractor(private val IFilmRepository: IFilmRepository) : FilmUseCase {

    override fun getAllMovie() = IFilmRepository.getAllMovie()

    override fun getAllFavMovie() = IFilmRepository.getAllFavMovie()

    override fun updateFavMovie(movie: Movie, state: Boolean) =
        IFilmRepository.setMovieFav(movie, state)

    override fun getAllTvShow() = IFilmRepository.getAllTvShow()

    override fun getAllFavTvShow() = IFilmRepository.getAllFavTvShow()

    override fun updateFavTvShow(tvShow: TvShow, state: Boolean) =
        IFilmRepository.setTvShowFav(tvShow, state)

}