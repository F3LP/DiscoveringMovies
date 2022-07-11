package com.facd.discoveringmovies.domain.model.search_movie_by_id_response


import com.google.gson.annotations.SerializedName

data class SearchMovieByIdResponse(
    @SerializedName("directors")
    val directors: String,
    @SerializedName("genres")
    val genres: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imDbRating")
    val imDbRating: String,
    @SerializedName("imDbRatingVotes")
    val imDbRatingVotes: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("plot")
    val plot: String,
    @SerializedName("runtimeMins")
    val runtimeMins: String,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)