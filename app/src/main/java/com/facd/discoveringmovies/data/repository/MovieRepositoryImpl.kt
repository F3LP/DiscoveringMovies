package com.facd.discoveringmovies.data.repository

import com.facd.discoveringmovies.data.api.ApiImdbInterface
import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.data.util.safeApiCall
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import com.facd.discoveringmovies.domain.model.search_movie_by_id_response.SearchMovieByIdResponse
import com.facd.discoveringmovies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class MovieRepositoryImpl(
    private val service: ApiImdbInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override suspend fun searchMovieByTitle(title: String): ResultWrapper<SearchMovieByTitleResponse> =
         safeApiCall(dispatcher) { service.searchMovieByTitle(expression = title) }

    override suspend fun searchMovieById(id: String): ResultWrapper<SearchMovieByIdResponse> =
        safeApiCall(dispatcher) { service.searchMovieById(id = id) }


}
