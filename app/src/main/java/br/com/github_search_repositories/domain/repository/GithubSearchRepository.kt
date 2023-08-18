package br.com.github_search_repositories.domain.repository

import br.com.github_search_repositories.domain.models.GithubSearchRepositories

interface GithubSearchRepository {
    suspend fun getAllRepositoriesByUser(user: String?): List<GithubSearchRepositories>
}