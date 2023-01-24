package com.example.p6_flows.ui.fragment.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p6_flows.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import com.example.p6_flows.ui.fragment.main.MainContract.State
import com.example.p6_flows.ui.fragment.utils.Utils
import com.example.p6_flows.utils.NetworkResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    @ApplicationContext
    val appContext: Context,

    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<State> by lazy {
        MutableStateFlow(State())
    }
    val uiState: StateFlow<State> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()


    fun handleEvent(event: MainContract.Event) {
        when (event) {
            MainContract.Event.GetData -> {
                getData()
            }
            MainContract.Event.ShowMessage -> {
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                movieRepository.fetchNowPlayingMovies()
                    .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                                //_uiError.send(result.message ?: "Error")
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movies = result.data ?: emptyList(), isLoading = false
                                )
                            }

                        }
                    }
            }
            else {
                _uiState.update {
                    it.copy(
                        error = "No hay internet cargando de cache.",
                        isLoading = false
                    )
                }
                movieRepository.fetchNowPlayingMovies()
                    .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                                //_uiError.send(result.message ?: "Error")
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movies = result.data ?: emptyList(), isLoading = false
                                )
                            }

                        }
                    }

            }
            //                  if (!Utils.hasInternetConnection(appContext))
            //                      _uiError.send("no hay internet"+ BuildConfig.API_KEY)
            //                     // _uiState.value = UiState.Failure("no hay internet")
            //                  else
            //                      _uiError.send("hay internet")
            //                      //_uiState.value = UiState.Failure("hay internet")
        }
    }

}