package com.facd.discoveringmovies.moviedetails

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelDetailModule = module {
    viewModel { MovieDetailsViewModel(get()) }
}