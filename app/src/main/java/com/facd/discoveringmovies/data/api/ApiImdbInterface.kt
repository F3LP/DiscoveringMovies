package com.facd.discoveringmovies.data.api

import com.facd.discoveringmovies.BuildConfig
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import com.facd.discoveringmovies.domain.model.search_movie_by_id_response.SearchMovieByIdResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiImdbInterface {

    @GET("{lang}/API/SearchTitle/{apiKey}/{expression}")
    suspend fun searchMovieByTitle(
        @Path("lang") lang: String? = "en",
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Path("expression") expression: String,
    ): SearchMovieByTitleResponse

    @GET("{lang}/API/Title/{apiKey}/{id}/{options}")
    suspend fun searchMovieById(
        @Path("lang") lang: String? = "en",
        @Path("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Path("id") id: String,
        @Path("options") options: String? = "",
    ): SearchMovieByIdResponse

}