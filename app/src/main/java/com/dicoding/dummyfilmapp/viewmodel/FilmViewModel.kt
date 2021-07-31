package com.dicoding.dummyfilmapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.dummyfilmapp.core.domain.useCase.FilmUseCase

class FilmViewModel(filmUseCase: FilmUseCase) : ViewModel() {
    val movie = filmUseCase.getAllMovie().asLiveData()
    val tvShow = filmUseCase.getAllTvShow().asLiveData()
}