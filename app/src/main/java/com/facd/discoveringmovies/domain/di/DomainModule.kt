package com.facd.discoveringmovies.domain.di

import com.facd.discoveringmovies.data.api.ApiImdbInterface
import com.facd.discoveringmovies.data.repository.MovieRepositoryImpl
import com.facd.discoveringmovies.domain.repository.MovieRepository
import com.facd.discoveringmovies.domain.usecase.SearchMovieByIdUseCase
import com.facd.discoveringmovies.domain.usecase.SearchMovieByTitleUseCase
import org.koin.dsl.module

val repositoryModule = module {
    factory { provideMovieRepository(get()) }
}

val useCaseModule = module {
    factory { provideSearchMovieByIdUseCase(get()) }
    factory { provideSearchMovieByTitleUseCase(get()) }
}

fun provideMovieRepository(service: ApiImdbInterface): MovieRepository {
    return MovieRepositoryImpl(service)
}

fun provideSearchMovieByIdUseCase(repository: MovieRepository): SearchMovieByIdUseCase {
    return SearchMovieByIdUseCase(repository)
}

fun provideSearchMovieByTitleUseCase(repository: MovieRepository): SearchMovieByTitleUseCase {
    return SearchMovieByTitleUseCase(repository)
}