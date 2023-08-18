package br.com.github_search_repositories.domain.usecases

import br.com.github_search_repositories.domain.repository.GithubSearchRepository
import br.com.github_search_repositories.extensions.others.executeFlow
import javax.inject.Inject

class GetAllRepositoriesByUserUseCase @Inject constructor(private val repository: GithubSearchRepository) {
    suspend operator fun invoke(user: String) = executeFlow(
        getRepository = {
            repository.getAllRepositoriesByUser(user)
        }
    )
}