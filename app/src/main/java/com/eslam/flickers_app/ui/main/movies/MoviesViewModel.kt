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

/**
 * MoviesViewModel is responsible for preparing and managing the data for the Movies UI.
 * It interacts with the GetMoviesUseCase to fetch the list of movies,
 * applying any search query filters and grouping the movies by year.
 * The ViewModel maintains a MutableStateFlow to emit the current state of movie data,
 * including loading, success, and error states, which the UI observes and reacts to.
 * Dependency injection is used via Hilt to provide the GetMoviesUseCase instance.
 * **/
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private var _getMoviesResponse =
        MutableStateFlow<DataState<List<YearMovies>>>(DataState.Idle)
    val getMoviesResponse = _getMoviesResponse.asStateFlow()

    fun getMovies(searchQuery: String? = null) {
        viewModelScope.launch {
            _getMoviesResponse.value = DataState.Loading
            getMoviesUseCase(searchQuery).collect { response ->
                /**
                 * If response is empty then set error state to handle it in UI
                 * else set success state to show the data
                 * */
                if (response.isEmpty()) {
                    _getMoviesResponse.value = DataState.Error(LocalExceptions.UnknownException)
                } else {
                    _getMoviesResponse.value = DataState.Success(response)
                }
            }
        }
    }
}