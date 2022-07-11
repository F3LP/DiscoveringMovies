package com.facd.discoveringmovies.moviedetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facd.discoveringmovies.R
import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.domain.model.search_movie_by_id_response.SearchMovieByIdResponse
import com.facd.discoveringmovies.domain.usecase.SearchMovieByIdUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val searchMovieByIdUseCase: SearchMovieByIdUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<SearchMovieByIdResponse?>()
    val movie: LiveData<SearchMovieByIdResponse?> = _movie

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun searchMovieById(id: String) {
        viewModelScope.launch {
            _loading.value = true

            when (val result = searchMovieByIdUseCase.searchMovieById(id)) {
                is ResultWrapper.NetworkError -> Log.e(
                    R.string.msg_error.toString(),
                    R.string.network_error.toString()
                )
                is ResultWrapper.GenericError -> Log.e(
                    R.string.msg_error.toString(),
                    R.string.network_error.toString()
                )
                is ResultWrapper.Success -> {
                    _movie.value = result.value
                    _loading.value = false
                }
            }
        }
    }

    fun clearMovieDetails() {
        _movie.value = null
    }
}