package br.com.github_search_repositories.presentation.mock

import br.com.github_search_repositories.domain.models.GithubSearchRepositories
import br.com.github_search_repositories.domain.models.Owner

val dummyOwner = Owner(
    avatarUrl = "https://avatars.githubusercontent.com/u/67604155?v=4",
    htmlUrl = "https://github.com/marceloalves95",
    login = "marceloalves95",
    reposUrl = "https://api.github.com/users/marceloalves95/repos",
    url = "https://api.github.com/users/marceloalves95"
)

val dummyGithubSearchRepositories = GithubSearchRepositories(
    createdAt = "2020-10-25T15:04:38Z",
    description = "Projeto feito com a linguagem Kotlin, e com funcionalidades básicas de um SGDB (Sistema Gerenciador de Banco de Dados) usando o banco de dados SQLite.",
    fullName = "marceloalves95/Agenda-Telefonica",
    language = "Kotlin",
    name = "Agenda-Telefonica",
    owner = dummyOwner,
    pushedAt = "2021-06-29T20:26:23Z",
    size = 561,
    updatedAt = "2021-06-29T20:26:26Z",
    url = "https://api.github.com/repos/marceloalves95/Agenda-Telefonica",
    htmlUrl = "https://github.com/marceloalves95/Agenda-Telefonica"
)