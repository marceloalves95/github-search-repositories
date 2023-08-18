package br.com.github_search_repositories.data.api

import br.com.github_search_repositories.data.model.GithubSearchRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubSearchRepositoryApi {
    @GET("users/{user}/repos")
    suspend fun getAllRepositoriesByUser(@Path("user") user: String?): Response<List<GithubSearchRepositoryResponse>>
}