package com.facd.discoveringmovies.domain.repository

import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import com.facd.discoveringmovies.domain.model.search_movie_by_id_response.SearchMovieByIdResponse

interface MovieRepository {

    suspend fun searchMovieByTitle(title: String): ResultWrapper<SearchMovieByTitleResponse>

    suspend fun searchMovieById(id: String): ResultWrapper<SearchMovieByIdResponse>

}

