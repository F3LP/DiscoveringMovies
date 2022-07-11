package com.facd.discoveringmovies.domain.usecase

import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import com.facd.discoveringmovies.domain.repository.MovieRepository

class SearchMovieByTitleUseCase(private val movieRepository: MovieRepository) {

    suspend fun searchMovieByTitle(title: String): ResultWrapper<SearchMovieByTitleResponse> =
        movieRepository.searchMovieByTitle(title)
}