package com.facd.discoveringmovies.domain.usecase

import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.domain.model.search_movie_by_id_response.SearchMovieByIdResponse
import com.facd.discoveringmovies.domain.repository.MovieRepository

class SearchMovieByIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun searchMovieById(id: String): ResultWrapper<SearchMovieByIdResponse> =
        movieRepository.searchMovieById(id)

}