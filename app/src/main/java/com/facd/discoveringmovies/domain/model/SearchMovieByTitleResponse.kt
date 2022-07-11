package com.facd.discoveringmovies.domain.model

data class SearchMovieByTitleResponse(
    val errorMessage: String,
    val expression: String,
    val results: List<CinemaTitle>,
    val searchType: String
)

data class CinemaTitle(
    val description: String,
    val id: String,
    val image: String,
    val resultType: String,
    val title: String
)

