package com.facd.discoveringmovies.listmovies

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelListMovieModule = module {
    viewModel { ListMovieViewModel(get()) }
}