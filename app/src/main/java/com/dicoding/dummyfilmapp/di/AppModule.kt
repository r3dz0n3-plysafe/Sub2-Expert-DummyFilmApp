package com.dicoding.dummyfilmapp.di

import com.dicoding.dummyfilmapp.core.domain.useCase.FilmInteractor
import com.dicoding.dummyfilmapp.core.domain.useCase.FilmUseCase
import com.dicoding.dummyfilmapp.viewmodel.DetailFilmViewModel
import com.dicoding.dummyfilmapp.viewmodel.FilmViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { FilmViewModel(get()) }
    viewModel { DetailFilmViewModel(get()) }
}