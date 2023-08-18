package br.com.github_search_repositories.data.mapper

import br.com.github_search_repositories.data.model.GithubSearchRepositoryResponse
import br.com.github_search_repositories.data.model.OwnerResponse
import br.com.github_search_repositories.domain.models.GithubSearchRepositories
import br.com.github_search_repositories.domain.models.Owner

internal fun GithubSearchRepositoryResponse.toGithubSearchRepositories() = GithubSearchRepositories(
    createdAt = createdAt,
    description = description,
    fullName = fullName,
    language = language,
    name = name,
    owner = owner?.toOwner(),
    pushedAt = pushedAt,
    size = size,
    updatedAt = updatedAt,
    url = url,
    htmlUrl = htmlUrl
)

internal fun OwnerResponse.toOwner() = Owner(
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    login = login,
    reposUrl = reposUrl,
    url = url
)