package com.calyrsoft.ucbp1.features.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.movie.domain.model.MovieModel
import com.calyrsoft.ucbp1.features.movie.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getPopularMovies: GetPopularMoviesUseCase
) : ViewModel() {

    sealed class UiState {
        data object Init : UiState()
        data object Loading : UiState()
        data class Success(val movies: List<MovieModel>) : UiState()
        data class Error(val message: String) : UiState()
    }

    private val _state = MutableStateFlow<UiState>(UiState.Init)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        observeLocal()
        refresh() // primera carga: API -> DB
    }

    fun observeLocal() {
        viewModelScope.launch {
            getPopularMovies.observe()
                .catch { _state.value = UiState.Error(it.message ?: "Error observando DB") }
                .collect { list -> _state.value = UiState.Success(list) }
        }
    }

    fun refresh(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            val result = getPopularMovies(page)
            _state.value = result.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Error al refrescar") }
            )
        }
    }
}
