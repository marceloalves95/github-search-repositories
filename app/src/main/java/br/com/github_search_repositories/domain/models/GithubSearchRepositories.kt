package br.com.github_search_repositories.domain.models

data class GithubSearchRepositories(
    val createdAt: String?,
    val description: String?,
    val fullName: String?,
    val language: String?,
    val name: String?,
    val owner: Owner?,
    val pushedAt: String?,
    val size: Int?,
    val updatedAt: String?,
    val url: String?,
    val htmlUrl: String?
)

data class Owner(
    val avatarUrl: String?,
    val htmlUrl: String?,
    val login: String?,
    val reposUrl: String?,
    val url: String?
)