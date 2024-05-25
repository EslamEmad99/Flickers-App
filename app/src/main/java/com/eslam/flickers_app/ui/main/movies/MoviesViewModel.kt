package com.eslam.flickers_app.ui.main.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslam.domain.exceptions.LocalExceptions
import com.eslam.domain.model.YearMovies
import com.eslam.domain.use_case.main.GetMoviesUseCase
import com.eslam.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private var _getMoviesResponse =
        MutableStateFlow<DataState<List<YearMovies>>>(DataState.Loading)
    val getMoviesResponse = _getMoviesResponse.asStateFlow()

    init {
        getMovies()
    }

    fun getMovies(searchQuery: String? = null) {
        viewModelScope.launch {
            _getMoviesResponse.value = DataState.Loading
            getMoviesUseCase(searchQuery).collect { response ->
                if (response.isEmpty()) {
                    _getMoviesResponse.value = DataState.Error(LocalExceptions.UnknownException)
                } else {
                    _getMoviesResponse.value = DataState.Success(response)
                }
            }
        }
    }
}