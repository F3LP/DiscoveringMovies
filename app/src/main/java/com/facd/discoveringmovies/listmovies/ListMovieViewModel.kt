package com.facd.discoveringmovies.listmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facd.discoveringmovies.R
import com.facd.discoveringmovies.data.util.ResultWrapper
import com.facd.discoveringmovies.domain.model.SearchMovieByTitleResponse
import com.facd.discoveringmovies.domain.usecase.SearchMovieByTitleUseCase
import kotlinx.coroutines.launch

class ListMovieViewModel(
    private val searchMovieByTitleUseCase: SearchMovieByTitleUseCase
    ) : ViewModel() {

    private var _movie = MutableLiveData<SearchMovieByTitleResponse?>()
    val movie: LiveData<SearchMovieByTitleResponse?> = _movie

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun searchMovieByTitle(title: String) {
        viewModelScope.launch {
            _loading.value = true

            when (val result = searchMovieByTitleUseCase.searchMovieByTitle(title)) {
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

    fun clearListMovies() {
        _movie.value = null
    }

}










