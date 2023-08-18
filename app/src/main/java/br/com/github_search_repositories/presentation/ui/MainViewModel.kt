package br.com.github_search_repositories.presentation.ui

import androidx.lifecycle.ViewModel
import br.com.github_search_repositories.domain.usecases.GetAllRepositoriesByUserUseCase
import br.com.github_search_repositories.extensions.others.launch
import br.com.github_search_repositories.network.event.Event
import br.com.github_search_repositories.presentation.model.GithubSearchRepositoriesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllRepositoriesByUserUseCase: GetAllRepositoriesByUserUseCase
) : ViewModel() {

    private val _state =
        MutableStateFlow<GithubSearchRepositoriesUIState>(GithubSearchRepositoriesUIState.Loading)
    val state: StateFlow<GithubSearchRepositoriesUIState> get() = _state

    fun loadAllRepositoriesByUser(user: String) = launch {
        getAllRepositoriesByUserUseCase.invoke(user).collect { event ->
            when (event) {
                is Event.Loading -> {
                    _state.value = GithubSearchRepositoriesUIState.Loading
                }

                is Event.Data -> {
                    _state.value = GithubSearchRepositoriesUIState.ScreenData(event.data)
                }

                is Event.Error -> {
                    _state.value = GithubSearchRepositoriesUIState.Error(event.error)
                }
            }
        }
    }
}