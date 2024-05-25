package com.eslam.flickers_app.ui.main.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.domain.model.FlickersData
import com.eslam.domain.use_case.main.GetMovieFlickersUseCase
import com.eslam.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieFlickersUseCase: GetMovieFlickersUseCase
) : ViewModel() {

    private var _getMovieDetailsResponse =
        MutableStateFlow<DataState<FlickersData>>(DataState.Idle)
    val getMovieDetailsResponse = _getMovieDetailsResponse.asStateFlow()

    fun getMovieDetails(movieTitle: String) {
        viewModelScope.launch {
            getMovieFlickersUseCase(movieTitle = movieTitle).collect {
                _getMovieDetailsResponse.value = it
            }
        }
    }
}