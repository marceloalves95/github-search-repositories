package br.com.github_search_repositories.data

import br.com.github_search_repositories.data.api.GithubSearchRepositoryApi
import br.com.github_search_repositories.data.mapper.toGithubSearchRepositories
import br.com.github_search_repositories.domain.models.GithubSearchRepositories
import br.com.github_search_repositories.domain.repository.GithubSearchRepository
import br.com.github_search_repositories.extensions.network.parseResponse
import br.com.github_search_repositories.extensions.network.toResponse

class GithubSearchRepositoryImpl(
    private val api: GithubSearchRepositoryApi
) : GithubSearchRepository {
    override suspend fun getAllRepositoriesByUser(user: String?): List<GithubSearchRepositories> {
        return api.getAllRepositoriesByUser(user).parseResponse().toResponse()
            .map { it.toGithubSearchRepositories() }
    }
}