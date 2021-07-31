package com.dicoding.dummyfilmapp.favourite.di

import com.dicoding.dummyfilmapp.favourite.ui.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    viewModel { FavouriteViewModel(get()) }
}