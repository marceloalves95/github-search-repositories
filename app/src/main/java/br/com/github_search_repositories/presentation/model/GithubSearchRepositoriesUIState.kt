package br.com.github_search_repositories.presentation.model

import br.com.github_search_repositories.domain.models.GithubSearchRepositories

sealed class GithubSearchRepositoriesUIState {
    object Loading : GithubSearchRepositoriesUIState()
    data class ScreenData(val screenData: List<GithubSearchRepositories>) :
        GithubSearchRepositoriesUIState()

    data class Error(val exception: Throwable? = null) : GithubSearchRepositoriesUIState()
}
