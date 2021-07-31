package com.dicoding.dummyfilmapp.favourite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.dummyfilmapp.core.domain.useCase.FilmUseCase

class FavouriteViewModel(filmUseCase: FilmUseCase) : ViewModel() {
    val movieFav = filmUseCase.getAllFavMovie().asLiveData()
    val tvShowFav = filmUseCase.getAllFavTvShow().asLiveData()
}